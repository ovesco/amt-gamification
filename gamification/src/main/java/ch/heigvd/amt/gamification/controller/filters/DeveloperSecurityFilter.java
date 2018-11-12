package ch.heigvd.amt.gamification.controller.filters;

import ch.heigvd.amt.gamification.Util.ServletUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeveloperSecurityFilter extends BaseSecurityFilter {
    @Override
    protected String getProtectedPath() {
        return "/developer";
    }

    @Override
    Boolean accessGranted(HttpServletRequest request, HttpServletResponse response) {

        return ServletUtil.getAccountId(request) != null;
    }
}
