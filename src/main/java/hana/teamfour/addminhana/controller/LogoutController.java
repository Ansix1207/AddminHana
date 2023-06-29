package hana.teamfour.addminhana.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout/*")
public class LogoutController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        System.out.println("pathInfo: " + pathInfo);
        switch (pathInfo) {
            case "/user":
                request.getSession().invalidate(); // 세션 삭제
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            case "/customer":
                request.getSession().removeAttribute("customerSession"); // 세션 삭제
                response.sendRedirect(request.getContextPath());
        }
    }

}
