package hana.teamfour.addminhana.controller;

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
                /*System.out.println("Acc_id: " + request.getParameter("Acc_id"));
                System.out.println("Acc_cid: " + request.getParameter("Acc_cid"));
                System.out.println("Acc_date: " + request.getParameter("Acc_date"));
                System.out.println("Acc_balance: " + request.getParameter("Acc_balance"));
                System.out.println("ACC_PASSWORD: " + request.getParameter("ACC_PASSWORD"));

                System.out.println("Acc_pid: " + request.getParameter("Acc_pid"));
                System.out.println("Acc_p_category: " + request.getParameter("Acc_p_category"));
                System.out.println("Acc_pname: " + request.getParameter("Acc_pname"));
                System.out.println("ACC_INTERESTRATE: " + request.getParameter("ACC_INTERESTRATE"));
                System.out.println("ACC_COLLATERALVALUE: " + request.getParameter("ACC_COLLATERALVALUE"));

                System.out.println("ACC_INTEREST_DAY: " + request.getParameter("ACC_INTEREST_DAY"));
                System.out.println("ACC_CONTRACT_MONTH: " + request.getParameter("ACC_CONTRACT_MONTH"));
                System.out.println("ACC_MATURITYDATE: " + request.getParameter("ACC_MATURITYDATE"));
                System.out.println("ACC_ISACTIVE: " + request.getParameter("ACC_ISACTIVE"));*/

                System.out.println("Post 진입");

                ProductJoinDTO productJoinDTO = new ProductJoinDTO();
                LocalDateTime currentDateTime = LocalDateTime.now();

                productJoinDTO.setAcc_id(Integer.parseInt(request.getParameter("Acc_id")));
                productJoinDTO.setAcc_cid(Integer.parseInt(request.getParameter("Acc_cid")));
                productJoinDTO.setAcc_date(java.sql.Timestamp.valueOf(currentDateTime));
                productJoinDTO.setAcc_balance(Integer.parseInt(request.getParameter("Acc_balance")));
                productJoinDTO.setAcc_password(request.getParameter("ACC_PASSWORD"));

                productJoinDTO.setAcc_pid(Integer.parseInt(request.getParameter("Acc_pid")));
                productJoinDTO.setAcc_p_category(request.getParameter("Acc_p_category"));
                productJoinDTO.setAcc_pname(request.getParameter("Acc_pname"));
                productJoinDTO.setAcc_interestrate(Double.valueOf(request.getParameter("ACC_INTERESTRATE")));
                productJoinDTO.setAcc_collateralvalue(Integer.valueOf(request.getParameter("ACC_COLLATERALVALUE")));

                productJoinDTO.setAcc_interest_day(Integer.valueOf(request.getParameter("ACC_INTEREST_DAY")));
                productJoinDTO.setAcc_contract_month(Integer.valueOf(request.getParameter("ACC_CONTRACT_MONTH")));
                productJoinDTO.setAcc_maturitydate(java.sql.Timestamp.valueOf(currentDateTime));
                productJoinDTO.setAcc_isactive('Y');

                loanJoinService.insertLoanJoin(productJoinDTO);
                /* insertLoanJoin이 ProductJoinDTO를 받아와서 반환하여 loanJoinDAO 에서 삽입
                 */
                /*
                request.setAttribute("Acc_id", request.getParameter("Acc_id"));
                request.setAttribute("Acc_cid", request.getParameter("Acc_cid"));
                request.setAttribute("Acc_date", request.getParameter("Acc_date"));
                request.setAttribute("Acc_balance", request.getParameter("Acc_balance"));
                request.setAttribute("ACC_PASSWORD", request.getParameter("ACC_PASSWORD"));
                request.setAttribute("Acc_pid", request.getParameter("Acc_pid"));
                request.setAttribute("Acc_p_category", request.getParameter("Acc_p_category"));
                request.setAttribute("Acc_pname", request.getParameter("Acc_pname"));
                request.setAttribute("ACC_INTERESTRATE", request.getParameter("ACC_INTERESTRATE"));
                request.setAttribute("ACC_COLLATERALVALUE", request.getParameter("ACC_COLLATERALVALUE"));
                request.setAttribute("ACC_INTEREST_DAY", request.getParameter("ACC_INTEREST_DAY"));
                request.setAttribute("ACC_CONTRACT_MONTH", request.getParameter("ACC_CONTRACT_MONTH"));
                request.setAttribute("ACC_MATURITYDATE", request.getParameter("ACC_MATURITYDATE"));
                request.setAttribute("ACC_ISACTIVE", request.getParameter("ACC_ISACTIVE"));
                System.out.println(request.getParameter("Acc_cid"));
                System.out.println("POST 요청 처리 끝" + request);
                 */
                System.out.println("POST 요청 처리 끝" + request);
                dispatcher = request.getRequestDispatcher("/views/loanJoin.jsp");
                dispatcher.forward(request, response);
                break;
            default:
        }
    }
}
