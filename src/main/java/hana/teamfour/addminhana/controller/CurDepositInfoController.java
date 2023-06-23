package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.DepositAssetDAO;
import hana.teamfour.addminhana.DAO.DepositAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.entity.ProductEntity;
import hana.teamfour.addminhana.service.DepositAssetService;
import hana.teamfour.addminhana.service.DepositAccountService;
import hana.teamfour.addminhana.service.DepositBalanceService;
import hana.teamfour.addminhana.service.RecommendService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/depositInfo")
public class CurDepositInfoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepositAccountDAO depositAccountDAO = new DepositAccountDAO();
        DepositAssetDAO depositAssetDAO = new DepositAssetDAO();

        DepositAccountService depositAccountService = new DepositAccountService(depositAccountDAO);
        DepositAssetService depositAssetService = new DepositAssetService(depositAssetDAO);
        DepositBalanceService depositBalanceService = new DepositBalanceService(depositAccountDAO);
        RecommendService recommendService = new RecommendService(37);

        AssetEntity assetEntity = depositAssetService.getDepositAsset();
        ArrayList<AccountEntity> accountEntity = depositAccountService.getDepositInfoList();
        Integer[] depositBalance = depositBalanceService.getDepositBalance();
        ArrayList<ProductEntity> recByJobProducts = recommendService.getRecByJob();
        ArrayList<ProductEntity> recByGenderProducts = recommendService.getRecByGender();
        ArrayList<ProductEntity> recByAgeProducts = recommendService.getRecByAge();

        request.setAttribute("accountEntity", accountEntity);
        request.setAttribute("assetEntity", assetEntity);
        request.setAttribute("depositBalance", depositBalance);
        request.setAttribute("productType", "예금");
        request.setAttribute("recByJob", recByJobProducts);
        request.setAttribute("recByGender", recByGenderProducts);;
        request.setAttribute("recByAge", recByAgeProducts);

        String site = "views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}
