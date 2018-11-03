package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Application;
import ch.heigvd.amt.gamification.Model.entity.Developer;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IApplicationDAOLocal;
import ch.heigvd.amt.gamification.services.dao.IDeveloperDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "applicationsServlet", urlPatterns = {"/developer/applications"})
public class ApplicationsServlet extends HttpServlet {

    @EJB
    private IApplicationDAOLocal applicationDAO;

    @EJB
    private IDeveloperDAOLocal developerDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer page            = ServletUtil.getInt(request.getParameter("page"), 0);
        Integer resultsPerPage  = ServletUtil.getInt(request.getParameter("amount"), 10);

        /*
        Application[] apps = new Application[50];
        for(int i = 0; i < 50; i++) {
            Application app = new Application();
            app.setName("Mon app " + i);
            app.setDescription("Une super description " + i);
            apps[i] = app;
        }

        Application[] result = new Application[resultsPerPage];

        // Paginate
        for(int i = 0; i < resultsPerPage; i++)
            result[i] = apps[resultsPerPage*page + i];
            */

        Long devId = getDevId(request);
        List<Application> apps = applicationDAO.findDeveloperApps(devId, page, resultsPerPage);
        Long amountOfApps = applicationDAO.countDeveloperApps(devId);

        request.setAttribute("applications", apps);
        request.setAttribute("pages", amountOfApps);
        request.getRequestDispatcher("/WEB-INF/pages/developer/applications.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processForm(new Application(), request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processForm(new Application(), request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void processForm(Application application, HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Long devId                      = getDevId(request);
        String appName                  = ServletUtil.getString(request.getParameter("name"), null);
        String appDescription           = ServletUtil.getString(request.getParameter("description"), null);
        HashMap<String, String> errors  = new HashMap<>();

        if(appName == null) errors.put("name", "Name can't be empty");
        if(appDescription == null) errors.put("description", "Description can't be empty");

        try {
            Developer developer  = developerDAO.find(devId);
        } catch (EntityNotFoundException e) {
            throw new ServletException();
        }
    }

    private Long getDevId(HttpServletRequest request) {
        return (Long)request.getSession().getAttribute("token");
    }
}
