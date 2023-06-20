package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    ServletContext context = null;
    CustomerService customerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
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
        String nextPage = "/views/profile.jsp";
        try {
            // 
            // 1. 로그인 후 /profile 의 경로로 컨트롤러를 타고 들어오면
            // 1.1. CustomerSummaryDTO 를 서비스단에서 가져와서 request에 setAttribute
            CustomerSummaryDTO customerSummaryDTO = customerService.getCustomerSummaryDTOById(1);
            request.setAttribute("customerSummaryDTO", customerSummaryDTO);

            // 2. AssetSummary
            // 3. description
            // 4. recommendation
            // 5. calendar data 

            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
