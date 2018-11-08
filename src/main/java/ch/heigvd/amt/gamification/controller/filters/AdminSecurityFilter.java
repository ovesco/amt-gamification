package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Util.SecurityToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminSecurityFilter extends  BaseSecurityFilter {
    @Override
    protected String getProtectedPath() {
        return "/admin";
    }

    @Override
    Boolean accessGranted(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession().getAttribute(SecurityToken.ADMIN_AUTH_TOKEN) != null;
    }
}
