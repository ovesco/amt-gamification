package ch.heigvd.amt.gamification.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DeveloperSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        boolean secured = true;

        System.out.println(path);
        if(path.startsWith("/static")) secured = false;
        else if(path.startsWith("/auth")) secured = false;
        else {
            request.setAttribute("targetUrl", path);
        }

        // Check if logged
        String token = (String) request.getSession().getAttribute("token");

        if(token == null && secured)
            request.getRequestDispatcher("/WEB-INF/pages/auth/auth.jsp").forward(servletRequest, servletResponse);
        else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
