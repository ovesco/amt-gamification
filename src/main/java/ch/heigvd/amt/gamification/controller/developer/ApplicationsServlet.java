package ch.heigvd.amt.gamification.controller.developer;

import ch.heigvd.amt.gamification.Model.entity.Application;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.IApplicationDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "applicationsServlet", urlPatterns = {"/developer/applications"})
public class ApplicationsServlet extends HttpServlet {

    @EJB
    private IApplicationDAOLocal applicationDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer page            = ServletUtil.getInt(request.getParameter("page"), 1);
        Integer resultsPerPage  = ServletUtil.getInt(request.getParameter("amount"), 10);
        Long devId              = ServletUtil.getAccountId(request);
        List<Application> apps  = applicationDAO.findDeveloperApps(devId, page-1, resultsPerPage);
        Long amountOfApps       = applicationDAO.countDeveloperApps(devId);

        request.setAttribute("applications", apps);
        request.setAttribute("pages", (int)Math.ceil(amountOfApps / Double.valueOf(resultsPerPage)));
        request.setAttribute("current", page);
        request.getRequestDispatcher("/WEB-INF/pages/developer/applications.jsp").forward(request, response);
    }
}
