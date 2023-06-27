package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.entity.ProductEntity;
import hana.teamfour.addminhana.service.RecommendService;
import hana.teamfour.addminhana.service.SavingsAccountService;
import hana.teamfour.addminhana.service.SavingsAssetService;
import hana.teamfour.addminhana.service.SavingsBalanceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/savingsInfo")
public class CurSavingsInfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productType = "적금";
        HttpSession session = request.getSession(false);

        CustomerSummaryDTO customerSummaryDTO = (CustomerSummaryDTO) session.getAttribute("userSession");
        Integer c_id = customerSummaryDTO.getC_id();
        System.out.println(c_id);

        SavingsAccountService savingsAccountService = new SavingsAccountService(c_id);
        SavingsAssetService savingsAssetService = new SavingsAssetService(c_id);
        SavingsBalanceService savingsBalanceService = new SavingsBalanceService(c_id);
        RecommendService recommendService = new RecommendService(c_id, productType);

        AssetEntity assetEntity = savingsAssetService.getSavingsAsset();
        ArrayList<AccountEntity> accountEntity = savingsAccountService.getSavingsAccList();
        Integer[] savingsBalance = savingsBalanceService.getSavingsBalance();
        ArrayList<ProductEntity> recByJobProducts = recommendService.getRecByJob();
        ArrayList<ProductEntity> recByGenderProducts = recommendService.getRecByGender();
        ArrayList<ProductEntity> recByAgeProducts = recommendService.getRecByAge();

        request.setAttribute("customerSummaryDTO", customerSummaryDTO);
        request.setAttribute("accountEntity", accountEntity);
        request.setAttribute("assetEntity", assetEntity);
        request.setAttribute("savingsBalance", savingsBalance);
        request.setAttribute("productType", productType);
        request.setAttribute("recByJob", recByJobProducts);
        request.setAttribute("recByGender", recByGenderProducts);;
        request.setAttribute("recByAge", recByAgeProducts);

        String site = "views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}