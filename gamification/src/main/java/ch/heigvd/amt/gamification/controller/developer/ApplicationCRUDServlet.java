package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Model.entity.Application;
import ch.heigvd.amt.gamification.Util.CRUD;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IApplicationDAOLocal;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "applicationCRUDServlet", urlPatterns = {"/developer/application"})
public class ApplicationCRUDServlet extends HttpServlet {

    @EJB
    IApplicationDAOLocal applicationDAO;

    @EJB
    IAccountDAOLocal accountDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action = ServletUtil.getString(request.getParameter("action"), CRUD.CREATE);

        if(action.equals(CRUD.CREATE))
            persist(request, response);
        else if(action.equals(CRUD.UPDATE))
            persist(request, response);
        else if(action.equals(CRUD.DELETE))
            delete(request, response);
        else
            throw new ServletException();
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long devId   = (Long)request.getSession().getAttribute("token");
        Long appId   = ServletUtil.getLong(request.getParameter("appId"), null);

        try {
            Application application = applicationDAO.find(appId);

            // Check that dev owns app
            if(!devId.equals(application.getAccount().getId()))
                throw new ServletException();

            applicationDAO.delete(application);
            response.sendRedirect(request.getContextPath() + "/developer/applications");

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }

    private void persist(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long devId              = ServletUtil.getAccountId(request);
        Long appId              = ServletUtil.getLong(request.getParameter("appId"), null);
        String action           = ServletUtil.getString(request.getParameter("action"), CRUD.CREATE);
        String appName          = ServletUtil.getString(request.getParameter("name"), null);
        String appDescription   = ServletUtil.getString(request.getParameter("description"), null);

        try {

            // Find or create application
            Application application = action.equals(CRUD.CREATE)
                    ? new Application()
                    : applicationDAO.find(appId);

            HashMap<String, String> errors  = new HashMap<>();
            if(appName == null) errors.put("name", "Name can't be empty");
            if(appDescription == null) errors.put("description", "Description can't be empty");

            // Errors found
            if(errors.size() > 0) {

                if(request.getMethod().equals("POST"))
                    request.setAttribute("errors", errors);

                request.setAttribute("action", action);
                request.setAttribute("name", appName == null ? application.getName() : appName);
                request.setAttribute("description", appDescription == null ? application.getDescription() : appDescription);
                request.getRequestDispatcher("/WEB-INF/pages/developer/applicationCRUD.jsp").forward(request, response);
            }

            // No errors
            else {

                application.setName(appName);
                application.setDescription(appDescription);

                if(action.equals(CRUD.CREATE)) {
                    Account account = accountDAO.find(devId);
                    application.setAccount(account);
                    applicationDAO.create(application);
                }
                else {
                    applicationDAO.update(application);
                }

                // Redirect back to applications list
                response.sendRedirect(request.getContextPath() + "/developer/applications");
            }

        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }
}
