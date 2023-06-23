package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSignDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;
import hana.teamfour.addminhana.entity.EmployeeEntity;
import hana.teamfour.addminhana.service.CustomerService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/sign")
public class SignController extends HttpServlet {
    ServletContext context = null;
    CustomerService customerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String action = request.getPathInfo();
        System.out.println("action = " + action);
        switch (request.getMethod()) {
            case "GET":
                System.out.println("get 진입");
                System.out.println(request.getParameter("check"));

                RequestDispatcher dispatcher = request.getRequestDispatcher("./views/sign.jsp");
                dispatcher.forward(request, response);
                break;
            case "POST":
                if (request.getParameter("check")!=null && request.getParameter("check").equals("do")) {
                    System.out.println("check 문 들어옴");
                    return;
                }
                EmployeeEntity user = (EmployeeEntity) request.getSession().getAttribute("login");
                CustomerSignDTO customerSignDTO = CustomerSignDTO.builder()
                        .c_name(request.getParameter("c_name"))
                        .c_rrn(request.getParameter("c_rrn"))
                        .c_gender(customerService.getGenderFromRRN(request.getParameter("c_rrn")))
                        .c_address(request.getParameter("c_address1") +" "+
                                request.getParameter("c_address2"))
                        .c_mobile(request.getParameter("c_mobile"))
                        .c_job(request.getParameter("c_job"))
                        .c_description(request.getParameter("c_description"))
                        .e_id(user.getE_id())
                        .build();
                System.out.println("post 진입");
                System.out.println("customerSignDTO = " + customerSignDTO.toString());
                System.out.println(customerService.signCustomer(customerSignDTO));
                break;
            default:
        }
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
