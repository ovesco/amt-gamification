package ch.heigvd.amt.gamification.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class BaseSecurityFilter implements Filter {

    /**
     * Returns the secured path this filter protects
     * @return string
     */
    abstract protected String getProtectedPath();

    /**
     * Checks if current user has access to secured part
     * @param request
     * @param response
     * @return true if access granted
     */
    abstract Boolean accessGranted(HttpServletRequest request, HttpServletResponse response);

    protected void accessDeniedAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/auth/login");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if(path.startsWith(getProtectedPath()) && !accessGranted(request, response))
            accessDeniedAction(request, response);
        else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
