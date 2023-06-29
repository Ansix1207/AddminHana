package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.EmployeeDTO;
import hana.teamfour.addminhana.service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./views/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        EmployeeService employeeService = new EmployeeService();
        EmployeeDTO employeeDTO = employeeService.login(id, pw);
        
        if (employeeDTO != null) {
            request.getSession().setAttribute("login", employeeDTO);
            request.getSession().setMaxInactiveInterval(60 * 30); // 60초 * 30분 (테스트할 때 세션 시간 늘리시면 됩니다)
            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("isLoginSuccess", "false");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./views/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
