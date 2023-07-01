package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.AccountDTO;
import hana.teamfour.addminhana.DTO.AssetDTO;
import hana.teamfour.addminhana.DTO.CustomerSessionDTO;
import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.ProductEntity;
import hana.teamfour.addminhana.service.AccountService;
import hana.teamfour.addminhana.service.AssetService;
import hana.teamfour.addminhana.service.CustomerService;
import hana.teamfour.addminhana.service.RecommendService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/customer/depositInfo", "/customer/loanInfo", "/customer/savingsInfo"})
public class AccInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String command = uri.substring(context.length());
        String category = null;
        switch (command) {
            case "/customer/depositInfo":
                category = "예금";
                break;
            case "/customer/savingsInfo":
                category = "적금";
                break;
            case "/customer/loanInfo":
                category = "대출";
                break;
            default:
                break;
        }

        HttpSession session = request.getSession(false);

        CustomerSessionDTO customerSessionDTO = (CustomerSessionDTO) session.getAttribute("customerSession");
        Integer c_id = customerSessionDTO.getC_id();
        String action = request.getParameter("action");

        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService(c_id, category);
        AssetService assetService = new AssetService(c_id, category);
        RecommendService recommendService = new RecommendService(c_id, category);

        CustomerSummaryDTO customerSummaryDTO = customerService.getCustomerSummaryDTOById(c_id);

        try {
            if (action != null && action.equals("asset-update")) {
                customerSummaryDTO = customerService.getCustomerSummaryDTOById(customerSessionDTO.getC_id());
                request.setAttribute("customerSummaryDTO", customerSummaryDTO);
                Integer customerId = customerSessionDTO.getC_id();
                assetService.refreshAssetTable(customerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AssetDTO assetDTO = assetService.getAsset();
        ArrayList<AccountDTO> accountDTOList = accountService.getAccList();
        ArrayList<ProductEntity> recByJobProducts = recommendService.getRecByJob();
        ArrayList<ProductEntity> recByGenderProducts = recommendService.getRecByGender();
        ArrayList<ProductEntity> recByAgeProducts = recommendService.getRecByAge();

        request.setAttribute("customerSummaryDTO", customerSummaryDTO);
        request.setAttribute("category", category);
        request.setAttribute("assetDTO", assetDTO);
        request.setAttribute("accountDTO", accountDTOList);
        request.setAttribute("recByJob", recByJobProducts);
        request.setAttribute("recByGender", recByGenderProducts);;
        request.setAttribute("recByAge", recByAgeProducts);

        String site = "../views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}
