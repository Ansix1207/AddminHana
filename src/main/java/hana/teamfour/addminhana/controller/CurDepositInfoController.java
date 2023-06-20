package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.DepositAssetDAO;
import hana.teamfour.addminhana.DAO.DepositAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.service.DepositAssetService;
import hana.teamfour.addminhana.service.DepositAccountService;
import hana.teamfour.addminhana.service.DepositRateService;

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
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }


    protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DepositAccountDAO depositAccountDAO = new DepositAccountDAO();
        DepositAssetDAO depositAssetDAO = new DepositAssetDAO();

        DepositAccountService depositAccountService = new DepositAccountService(depositAccountDAO);
        DepositAssetService depositAssetService = new DepositAssetService(depositAssetDAO);
        DepositRateService depositRateService = new DepositRateService(depositAccountDAO);

        AssetEntity assetEntity = depositAssetService.getDepositAsset();
        ArrayList<AccountEntity> accountEntity = depositAccountService.getDepositInfoList();
        Integer[] depositRate = depositRateService.getDepositRate();

        request.setAttribute("accountEntity", accountEntity);
        request.setAttribute("assetEntity", assetEntity);
        request.setAttribute("depositRate", depositRate);
        request.setAttribute("productType", "예금");

        String site = "views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}
