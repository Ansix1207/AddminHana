package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.LoanAssetDAO;
import hana.teamfour.addminhana.DAO.LoanAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;
import hana.teamfour.addminhana.service.LoanAssetService;
import hana.teamfour.addminhana.service.LoanAccountService;
import hana.teamfour.addminhana.service.LoanRateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/loanInfo.do")
public class SessionOnAccInfoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }


    protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoanAccountDAO loanAccountDAO = new LoanAccountDAO();
        LoanAssetDAO loanAssetDAO = new LoanAssetDAO();

        LoanAccountService loanAccountService = new LoanAccountService(loanAccountDAO);
        LoanAssetService loanAssetService = new LoanAssetService(loanAssetDAO);
        LoanRateService loanRateService = new LoanRateService(loanAccountDAO);

        AssetEntity assetEntity = loanAssetService.getLoanAsset();
        ArrayList<AccountEntity> accountEntity = loanAccountService.getLoanInfoList();
        Integer[] loanRate = loanRateService.getLoanRate();

        request.setAttribute("accountEntity", accountEntity);
        request.setAttribute("assetEntity", assetEntity);
        request.setAttribute("loanRate", loanRate);

        String site = "views/sessionOnAccInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}
