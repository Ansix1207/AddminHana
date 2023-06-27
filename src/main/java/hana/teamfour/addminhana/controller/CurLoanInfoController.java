package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.entity.ProductEntity;
import hana.teamfour.addminhana.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/loanInfo")
public class CurLoanInfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productType = "대출";
        Integer uid = 25;

        LoanAccountService loanAccountService = new LoanAccountService(uid);
        LoanAssetService loanAssetService = new LoanAssetService(uid);
        LoanBalanceService loanBalanceService = new LoanBalanceService(uid);
        RecommendService recommendService = new RecommendService(uid, productType);
        CustomerService customerService = new CustomerService();

        CustomerSummaryDTO customerSummaryDTO = customerService.getCustomerSummaryDTOById(uid);
        AssetEntity assetEntity = loanAssetService.getLoanAsset();
        ArrayList<AccountEntity> accountEntity = loanAccountService.getLoanAccList();
        Integer[] loanBalance = loanBalanceService.getLoanBalance();
        ArrayList<ProductEntity> recByJobProducts = recommendService.getRecByJob();
        ArrayList<ProductEntity> recByGenderProducts = recommendService.getRecByGender();
        ArrayList<ProductEntity> recByAgeProducts = recommendService.getRecByAge();

        request.setAttribute("customerSummaryDTO", customerSummaryDTO);
        request.setAttribute("accountEntity", accountEntity);
        request.setAttribute("assetEntity", assetEntity);
        request.setAttribute("loanBalance", loanBalance);
        request.setAttribute("productType", productType);
        request.setAttribute("recByJob", recByJobProducts);
        request.setAttribute("recByGender", recByGenderProducts);;
        request.setAttribute("recByAge", recByAgeProducts);

        String site = "views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}