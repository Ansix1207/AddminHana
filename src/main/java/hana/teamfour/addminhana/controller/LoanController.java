package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DAO.LoanProductDAO;
import hana.teamfour.addminhana.DTO.ProductDTO;
import hana.teamfour.addminhana.service.LoanProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loaninquery")
public class LoanController extends HttpServlet {
    private LoanProductService loanProductService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String query = request.getParameter("q");
        String page_ = request.getParameter("p");

        if (query != null && !query.isEmpty()) {
            setSearchProductDTO(request, response);
            int page = 1;
            if (page_ != null) {
                page = Integer.parseInt(page_);
            }
        } else {
            setProductDTO(request, response);
            System.out.println("pass");
            int page = 1;
            if (page_ != null && !query.isEmpty()) {
                page = Integer.parseInt(page_);
            }
        }
        setProductCount(request, response);
        setAccountCount(request, response);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sessionOffProductInfo.jsp");
        dispatcher.forward(request, response);
    }

    public void setAccountCount(HttpServletRequest request, HttpServletResponse response) {
        LoanProductDAO loanProductDAO = new LoanProductDAO();
        Map<String, Integer> accountCountMap = new HashMap<>();
        accountCountMap = loanProductDAO.getAccountCountByCategory();
        request.setAttribute("accountCountMap", accountCountMap);
    }

    public void setProductCount(HttpServletRequest request, HttpServletResponse response) {
        LoanProductDAO loanProductDAO = new LoanProductDAO();
        String query = request.getParameter("q");
        int count = loanProductDAO.getProductCount(query);
        request.setAttribute("count", count);
    }

    private void setProductDTO(HttpServletRequest request, HttpServletResponse response) {
        LoanProductService loanProductService = new LoanProductService();
        String page_ = request.getParameter("p");
        int page = 1;
        if (page_ != null) {
            page = Integer.parseInt(page_);
        }
        ArrayList<ProductDTO> productDTOs = loanProductService.getProducts(page);
        request.setAttribute("productDTOs", productDTOs);
//         productDTOs라는 이름으로 상품 목록을 request 객체에 속성으로 설정합니다
    }

    private void setSearchProductDTO(HttpServletRequest request, HttpServletResponse response) {
        LoanProductService loanProductService = new LoanProductService();
        String query_ = request.getParameter("q");
        String query = "";
        String page_ = request.getParameter("p");
        if (query_ != null) {
            query = query_;
        }
        int page = 1;
        if (page_ != null) {
            page = Integer.parseInt(page_);
        }
        ArrayList<ProductDTO> productDTOs = loanProductService.getSearchLoanProductList(query, page);
        request.setAttribute("productDTOs", productDTOs);
    }
}
