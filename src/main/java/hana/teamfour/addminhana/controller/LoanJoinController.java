package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.ProductDTO;
import hana.teamfour.addminhana.DTO.ProductJoinDTO;
import hana.teamfour.addminhana.service.LoanJoinService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/customer/loanjoin")
public class LoanJoinController extends HttpServlet {
    private LoanJoinService loanJoinService;

    //    LoanJoinService 클래스의 메서드와 기능에 접근할 수 있습니다.
    public LoanJoinController() {
        this.loanJoinService = new LoanJoinService();

    }
//    문제는 loanJoinService 객체가 null인 상태를 해결하기위해서 초기화

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher;
        switch (request.getMethod()) {
            case "GET":
                System.out.println("get 진입");
                dispatcher = request.getRequestDispatcher("/views/loanJoin.jsp");
                dispatcher.forward(request, response);
                break;
            case "POST":
                System.out.println("Post 진입");
                ProductJoinDTO productJoinDTO = new ProductJoinDTO();
                ProductDTO productDTO = new ProductDTO();
                LocalDateTime currentDateTime = LocalDateTime.now();

                productJoinDTO.setAcc_id(Integer.parseInt(request.getParameter("Acc_id")));
                productJoinDTO.setAcc_cid(Integer.parseInt(request.getParameter("Acc_cid")));
                productJoinDTO.setAcc_date(java.sql.Timestamp.valueOf(currentDateTime));
                productJoinDTO.setAcc_balance(Integer.parseInt(request.getParameter("Acc_balance")));
                productJoinDTO.setAcc_password(request.getParameter("ACC_PASSWORD"));

                productDTO.setP_id(Integer.valueOf(request.getParameter("Acc_pid")));
                productDTO.setP_category(request.getParameter("Acc_p_category"));
                productDTO.setP_name(request.getParameter("Acc_pname"));
                productDTO.setP_interestrate(Double.valueOf(request.getParameter("ACC_INTERESTRATE")));
                productJoinDTO.setAcc_collateralvalue(Integer.valueOf(request.getParameter("ACC_COLLATERALVALUE")));

                productJoinDTO.setAcc_interest_day(Integer.valueOf(request.getParameter("ACC_INTEREST_DAY")));
                productDTO.setP_contract_month(Integer.valueOf(request.getParameter("ACC_CONTRACT_MONTH")));
                productJoinDTO.setAcc_maturitydate(java.sql.Timestamp.valueOf(currentDateTime));
                productJoinDTO.setAcc_isactive('Y');

                boolean isSuccess = loanJoinService.insertLoanJoin(productJoinDTO);
//                Controller에서 isSuccess 변수를 JSP 페이지로 전달하는 코드 추가
                request.setAttribute("isSuccess", isSuccess);
                System.out.println("POST 요청 처리 끝" + request);
                dispatcher = request.getRequestDispatcher("/views/loanJoin.jsp");
                dispatcher.forward(request, response);
                break;
            default:
        }
    }
}
