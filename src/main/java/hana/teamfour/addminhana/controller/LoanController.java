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
        
        //list?=title&q
//        사용자가 변수를 할지안할지모르니 임시변수
        String
        
        String field_ = request.getParameter("f");
        String query = request.getParameter("q");
        
        NoticeService sevice = new NoticeService();
        List<Notice> list = service.getNoticeList(field, query, 1);

        setProductEntity(request, response);

        //dispatcher를 통해서 원하는 jsp를 보여준다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/sessionOffProductInfo.jsp");
        dispatcher.forward(request, response);
    }

    // LoanProductDAO 및 LoanProductService 객체 생성: DAO와 Service 클래스를 생성하여 데이터베이스 작업을 수행하기 위해 사용됩니다.
    // openLoanProductView() 메서드: ProductEntity 객체를 생성하고, 해당 객체를 request 객체에 저장합니다. 그 후, /views/sessionOffProductInfo.jsp 뷰 페이지로 이동합니다.
    private void setProductEntity(HttpServletRequest request, HttpServletResponse response) {
        LoanProductDAO loanProductDAO = new LoanProductDAO();
        ArrayList<ProductEntity> productEntity = loanProductDAO.getLoanProductList();
        request.setAttribute("productEntity", productEntity);
    }
}
