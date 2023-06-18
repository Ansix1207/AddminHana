package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.LoanInfoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class mainController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        requestPro(request, response);
    }

    protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String command = uri.substring(context.length());
        String site = null;

        System.out.println("command: " + command);

        LoanInfoDAO loanInfoDao = new LoanInfoDAO();

        switch (command) {
            case "/main.do":
                site = "index.jsp";
                break;
            case "/loanInfo.do":
                site = loanInfoDao.showLoanAccount(request, response);
                break;
            default:
                break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(site);
        dispatcher.forward(request, response);
    }
}