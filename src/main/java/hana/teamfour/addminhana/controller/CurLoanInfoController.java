package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.LoanAccountDAO;
import hana.teamfour.addminhana.DAO.LoanAssetDAO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.entity.ProductEntity;
import hana.teamfour.addminhana.service.LoanAccountService;
import hana.teamfour.addminhana.service.LoanAssetService;
import hana.teamfour.addminhana.service.LoanBalanceService;
import hana.teamfour.addminhana.service.RecommendService;

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
        Integer uid = 37;

        LoanAccountDAO loanAccountDAO = new LoanAccountDAO();
        LoanAssetDAO loanAssetDAO = new LoanAssetDAO();

        LoanAccountService loanAccountService = new LoanAccountService(loanAccountDAO);
        LoanAssetService loanAssetService = new LoanAssetService(loanAssetDAO);
        LoanBalanceService loanBalanceService = new LoanBalanceService(loanAccountDAO);
        RecommendService recommendService = new RecommendService(uid);

        AssetEntity assetEntity = loanAssetService.getLoanAsset();
        ArrayList<AccountEntity> accountEntity = loanAccountService.getLoanInfoList();
        Integer[] loanBalance = loanBalanceService.getLoanBalance();
        ArrayList<ProductEntity> recByJobProducts = recommendService.getRecByJob(productType);
        ArrayList<ProductEntity> recByGenderProducts = recommendService.getRecByGender(productType);
        ArrayList<ProductEntity> recByAgeProducts = recommendService.getRecByAge(productType);

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