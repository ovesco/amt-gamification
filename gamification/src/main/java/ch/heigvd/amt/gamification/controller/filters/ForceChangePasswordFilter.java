package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Model.entity.Account;
import ch.heigvd.amt.gamification.Util.ServletUtil;
import ch.heigvd.amt.gamification.services.dao.EntityNotFoundException;
import ch.heigvd.amt.gamification.services.dao.IAccountDAOLocal;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForceChangePasswordFilter implements Filter {

    @EJB
    IAccountDAOLocal accountDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Long currentId  = ServletUtil.getAccountId(request);
        Boolean forceChange = false;

        try {

            if(currentId != null) {
                Account account = accountDAO.find(currentId);
                forceChange = account.getForceChangePassword();
            }

        } catch (EntityNotFoundException e) {
            // Do nothing
        }

        if(forceChange
                && !request.getRequestURI().contains("change-password")
                && !request.getRequestURI().contains("logout")
                && !request.getRequestURI().contains("static")) {
            ServletUtil.getFlashBag(request).danger("Please change password before continuing");
            response.sendRedirect(request.getContextPath() + "/developer/change-password");
        }

        else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
