package ch.heigvd.amt.gamification.controller.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "forgotPasswordServlet", urlPatterns = {"/auth/forgot-password"})
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/pages/auth/forgotPassword.jsp").forward(request, response);
    }
}
