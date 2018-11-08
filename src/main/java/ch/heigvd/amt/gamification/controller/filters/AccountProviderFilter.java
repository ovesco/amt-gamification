package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccountProviderFilter implements Filter {

    @EJB
    IAccountDAOLocal accountDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Long accountId = ServletUtil.getAccountId((HttpServletRequest)servletRequest);

        if(accountId != null) {
            try {
                Account account = accountDAO.find(accountId);
                servletRequest.setAttribute("currentAccount", account);
            } catch (EntityNotFoundException e) {
                // Do nothing
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
