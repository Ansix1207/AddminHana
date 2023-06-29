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

@WebServlet("/customer/profile/*")
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
        String profilePage = "/views/profile.jsp";
        String action = request.getParameter("action");
        String description = request.getParameter("descriptionText");
        String customerRRN = (String) request.getParameter("customerRRN");
        CustomerSessionDTO customerSession = (CustomerSessionDTO) session.getAttribute("customerSession");
        try {
            CustomerSummaryDTO customerSummaryDTO = null;
            if (action != null && action.equals("description")) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOById(customerSession.getC_id());
                System.out.println("customerSummaryDTO = " + customerSummaryDTO);
                customerSummaryDTO.setC_description(description);
                boolean hasUpdated = customerService.updateCustomerDescription(customerSummaryDTO);
                request.setAttribute("hasUpdatedDescription", hasUpdated);
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
                dispatcher.forward(request, response);
                return;
            }
            if (customerSession == null) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOByRRN(customerRRN);
                if (customerSummaryDTO == null) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }
            }
            if (customerSession != null && customerRRN != null) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOByRRN(customerRRN);
                if (customerSummaryDTO == null || (customerSummaryDTO.getC_id() != customerSession.getC_id())) {
                    response.sendRedirect(request.getContextPath() + "/");
                    return;
                }
            }
            if (customerSummaryDTO != null) {
                CustomerSessionDTO customerSessionDTO = customerSummaryDTO.getCustomerSessionDTO();
                session.setAttribute("customerSession", customerSessionDTO);
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
                dispatcher.forward(request, response);
                return;
            }
            forwardToMain(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void forwardToMain(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = "/views/main.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
