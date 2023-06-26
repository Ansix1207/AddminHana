package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.LoanProductDAO;
import hana.teamfour.addminhana.entity.ProductEntity;
import hana.teamfour.addminhana.service.LoanProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/loaninquery")
public class LoanController extends HttpServlet {
    private LoanProductService loanProductService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String query = request.getParameter("q");
//        page가 int가 아니라 정수를 받는 이유 : null을 받을수도 있어서
        String page_ = request.getParameter("p");
        System.out.println(query);
        if (query != null && !query.isEmpty()) {
            setSearchProductEntity(request, response);
            int page = 1;
            if (page_ != null)
                page = Integer.parseInt(page_);
            System.out.println("path");
        } else {
            setProductEntity(request, response);
            int page = 1;
            if (page_ != null && !query.isEmpty())
                page = Integer.parseInt(page_);
            System.out.println("else");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sessionOffProductInfo.jsp");
        dispatcher.forward(request, response);
    }


    private void setProductEntity(HttpServletRequest request, HttpServletResponse response) {
        LoanProductDAO loanProductDAO = new LoanProductDAO();
        String query_ = request.getParameter("q");
        String query = "";
        String page_ = request.getParameter("p");
        if (query_ != null) {
            query = query_;
        }
        int page = 1;
        if (page_ != null)
            page = Integer.parseInt(page_);
        ArrayList<ProductEntity> productEntity = loanProductDAO.getLoanProductList(query, page);
        request.setAttribute("productEntity", productEntity);
    }

    private void setSearchProductEntity(HttpServletRequest request, HttpServletResponse response) {
        LoanProductDAO loanProductDAO = new LoanProductDAO();
        String query_ = request.getParameter("q");
        String query = "";
        String page_ = request.getParameter("p");
        if (query_ != null) {
            query = query_;
        }
        int page = 1;
        if (page_ != null)
            page = Integer.parseInt(page_);
        ArrayList<ProductEntity> productEntity = loanProductDAO.getSearchLoanProductList(query, page);
        request.setAttribute("productEntity", productEntity); /* 이름설정 */
    }
}
