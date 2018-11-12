package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.SecurityToken;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;
import ch.heigvd.amt.gamification.services.session.IFlashBagLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TemplateProvidingFilter implements Filter {

    @EJB
    IAccountDAOLocal accountDAO;

    @EJB
    IFlashBagLocal flashBag;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Provide session messages
        servletRequest.setAttribute("_messages", flashBag.getMessages());

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Long accountId = ServletUtil.getAccountId(request);

        // Provide account
        if(accountId != null) {
            try {
                Account account = accountDAO.find(accountId);
                servletRequest.setAttribute("_currentAccount", account);
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
