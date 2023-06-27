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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile/*")
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
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=utf-8");
        String nextPage = "/views/profile.jsp";
        String action = request.getParameter("action");
        String description = request.getParameter("descriptionText");
        String customerRRN = (String) request.getParameter("customerRRN");
        try {
            // 1. 로그인 후 /profile 의 경로로 컨트롤러를 타고 들어오면
            // 1.1. CustomerSummaryDTO 를 서비스단에서 가져와서 request에 setAttribute
            CustomerSummaryDTO customerSummaryDTO = customerService.getCustomerSummaryDTOByRRN(customerRRN);
            System.out.println("customerSummaryDTO controller = " + customerSummaryDTO);
            if (customerSummaryDTO == null) {
                nextPage = "/views/main.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                dispatcher.forward(request, response);
            } else {
                session.setAttribute("userSession", customerSummaryDTO);
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                // 2. AssetSummary
                // 3. description
                if (action != null && action.equals("description")) {
                    customerSummaryDTO.setC_description(description);
                    boolean hasUpdated = customerService.updateCustomerDescription(customerSummaryDTO);
                    // ! false 일 떄 토스트 뜨지 않게 테스트중 
                    // TODO: profile 페이지에서 새로고침했을 때 Toast 뜨지 않도록 변경해야함
                    // TODO: 새로고침했을 때 attribute를 삭제해줘야 하는 방법이 뭘까 ?
                    // TODO: Toast를 한번 띄우고 attribute 삭제 콜을 해야할것 같기도 하고.. ?
                    request.setAttribute("hasUpdatedDescription", hasUpdated);
                }
                // 4. recommendation
                // 5. calendar data
                RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
