package ch.heigvd.amt.gamification.controller.admin;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "accountsServlet", urlPatterns = {"/admin/accounts"})
public class AccountsServlet extends HttpServlet {

    @EJB
    IAccountDAOLocal accountDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer page            = ServletUtil.getInt(request.getParameter("page"), 1);
        Integer resultsPerPage  = ServletUtil.getInt(request.getParameter("amount"), 5);
        List<Account> accounts  = accountDAO.findPaginate(page - 1, resultsPerPage);
        Long amountOfAccounts   = accountDAO.findAmount();

        request.setAttribute("accounts", accounts);
        request.setAttribute("pages", Math.ceil(amountOfAccounts / Double.valueOf(resultsPerPage)));
        request.setAttribute("current", page);
        request.getRequestDispatcher("/WEB-INF/pages/admin/accounts.jsp").forward(request, response);
    }
}
