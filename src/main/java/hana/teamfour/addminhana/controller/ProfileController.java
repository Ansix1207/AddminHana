package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSessionDTO;
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
        CustomerSessionDTO customerSession = (CustomerSessionDTO) session.getAttribute("customerSession");
        try {
            CustomerSummaryDTO customerSummaryDTO = null;
            if (customerSession != null) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOById(customerSession.getC_id());
            } else if (customerRRN != null) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOByRrn(customerRRN);
            }

            if (customerSummaryDTO != null) {
                CustomerSessionDTO customerSessionDTO = customerSummaryDTO.getCustomerSessionDTO();
                session.setAttribute("customerSession", customerSessionDTO);
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                if (action != null && action.equals("description")) {
                    customerSummaryDTO.setC_description(description);
                    boolean hasUpdated = customerService.updateCustomerDescription(customerSummaryDTO);
                    // ! false 일 떄 토스트 뜨지 않게 테스트중 
                    // TODO: profile 페이지에서 새로고침했을 때 Toast 뜨지 않도록 변경해야함
                    // TODO: 새로고침했을 때 attribute를 삭제해줘야 하는 방법이 뭘까 ?
                    // TODO: Toast를 한번 띄우고 attribute 삭제 콜을 해야할것 같기도 하고.. ?
                    request.setAttribute("hasUpdatedDescription", hasUpdated);
                    request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                dispatcher.forward(request, response);
                return;
            }
            nextPage = "/views/main.jsp";
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
