package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.*;
import hana.teamfour.addminhana.service.AccountService;
import hana.teamfour.addminhana.service.AssetService;
import hana.teamfour.addminhana.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/customer/profile/*")
public class ProfileController extends HttpServlet {
    CustomerService customerService;
    AssetService assetService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        customerService = new CustomerService();
        assetService = new AssetService();
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
            if (action != null && action.equals("asset-update")) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOById(customerSession.getC_id());
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                Integer customerId = customerSession.getC_id();
                assetService.refreshAssetTable(customerId);
            }
            if (action != null && action.equals("description")) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOById(customerSession.getC_id());
                customerSummaryDTO.setC_description(description);
                boolean hasUpdated = customerService.updateCustomerDescription(customerSummaryDTO);
                request.setAttribute("hasUpdatedDescription", hasUpdated);
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                setAssetsInfo(request, response);
                RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
                dispatcher.forward(request, response);
                return;
            }
            if (customerSession == null) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOByRRN(customerRRN);
                if (customerSummaryDTO == null) {
                    response.sendRedirect(request.getContextPath() + "/");
                    response.sendRedirect(request.getContextPath() + "/?message=customerLoginFail");
                    return;
                }
            } else {
                customerSummaryDTO = customerService.getCustomerSummaryDTOById(customerSession.getC_id());
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

                setAssetsInfo(request, response);

                RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
                dispatcher.forward(request, response);
                return;
            }
            forwardToMain(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAssetsInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        CustomerSessionDTO customerSessionDTO = (CustomerSessionDTO) session.getAttribute("customerSession");
        Integer c_id = customerSessionDTO.getC_id();

        AssetSetterDTO depositSetterDTO = new AssetSetterDTO(c_id, "예금", "depositDTO", "depositAccountList");
        AssetSetterDTO savingsSetterDTO = new AssetSetterDTO(c_id, "적금", "savingsDTO", "savingsAccountList");
        AssetSetterDTO loanSetterDTO = new AssetSetterDTO(c_id, "대출", "loanDTO", "loanAccountList");

        setAssetByCategory(request, depositSetterDTO);
        setAssetByCategory(request, savingsSetterDTO);
        setAssetByCategory(request, loanSetterDTO);
    }

    private void setAssetByCategory(HttpServletRequest request, AssetSetterDTO assetSetterDTO) throws ServletException, IOException {
        Integer c_id = assetSetterDTO.getC_id();
        String category = assetSetterDTO.getCategory();
        String assetDTOName = assetSetterDTO.getAssetDTOName();
        String accountListName = assetSetterDTO.getAccountListName();
        AccountService accountService = new AccountService(c_id, category);
        AssetService assetService = new AssetService(c_id, category);
        AssetDTO assetDTO = assetService.getAsset();
        ArrayList<AccountDTO> accountList = accountService.getAccList();
        request.setAttribute(assetDTOName, assetDTO);
        request.setAttribute(accountListName, accountList);
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
