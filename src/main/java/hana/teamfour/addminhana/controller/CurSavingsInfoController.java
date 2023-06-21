package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.SavingsAccountDAO;
import hana.teamfour.addminhana.DAO.SavingsAssetDAO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.service.SavingsAccountService;
import hana.teamfour.addminhana.service.SavingsAssetService;
import hana.teamfour.addminhana.service.SavingsBalanceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/savingsInfo")
public class CurSavingsInfoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }


    protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SavingsAccountDAO savingsAccountDAO = new SavingsAccountDAO();
        SavingsAssetDAO savingsAssetDAO = new SavingsAssetDAO();

        SavingsAccountService savingsAccountService = new SavingsAccountService(savingsAccountDAO);
        SavingsAssetService savingsAssetService = new SavingsAssetService(savingsAssetDAO);
        SavingsBalanceService savingsBalanceService = new SavingsBalanceService(savingsAccountDAO);

        AssetEntity assetEntity = savingsAssetService.getSavingsAsset();
        ArrayList<AccountEntity> accountEntity = savingsAccountService.getSavingsInfoList();
        Integer[] savingsBalance = savingsBalanceService.getSavingsBalance();

        request.setAttribute("accountEntity", accountEntity);
        request.setAttribute("assetEntity", assetEntity);
        request.setAttribute("savingsBalance", savingsBalance);
        request.setAttribute("productType", "적금");

        String site = "views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}
