package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.ProductJoinDTO;
import hana.teamfour.addminhana.entity.EmployeeEntity;
import hana.teamfour.addminhana.service.LoanJoinService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/customer/loanjoin")

public class LoanJoinController extends HttpServlet {
    private LoanJoinService loanJoinService;
    private Integer id;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loanJoinService = new LoanJoinService(id);
    }

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
                dispatcher = request.getRequestDispatcher(request.getContextPath() + "/views/loanJoin.jsp");
                dispatcher.forward(request, response);
                break;
            case "POST":
                System.out.println("Post 진입");
//                String res = doValidRRN(request);
                request.setAttribute("acc_id", request.getParameter("acc_id"));
                request.setAttribute("acc_cid", request.getParameter("acc_cid"));
                request.setAttribute("acc_date", request.getParameter("acc_date"));
                request.setAttribute("acc_balance", request.getParameter("acc_balance"));
                request.setAttribute("acc_password", request.getParameter("acc_password"));
                request.setAttribute("acc_pid", request.getParameter("acc_pid"));
                request.setAttribute("acc_p_category", request.getParameter("acc_p_category"));
                request.setAttribute("acc_pname", request.getParameter("acc_pname"));
                request.setAttribute("acc_interestrate", request.getParameter("acc_interestrate"));
                request.setAttribute("acc_collateralvalue", request.getParameter("acc_collateralvalue"));
                request.setAttribute("acc_interest_day", request.getParameter("acc_interest_day"));
                request.setAttribute("acc_contract_month", request.getParameter("acc_contract_month"));
                request.setAttribute("acc_maturitydate", request.getParameter("acc_maturitydate"));
                request.setAttribute("acc_isactive", request.getParameter("acc_isactive"));
                String res = doSign(request);
                dispatcher = request.getRequestDispatcher("./views/loanJoin.jsp");
                dispatcher.forward(request, response);
                System.out.println("POST 요청 처리 끝");
                break;
            default:
        }
    }

    private String doValidRRN(HttpServletRequest request) {
        String res = "중복된 주민등록 번호입니다.";
        request.setAttribute("ProductJoinDTO", makeSignDTO(request));
        return res;
    }

    /**
     * request로 전달받은 값들을 통해 LoanServiceDTO를 만들고 반환합니다. 고맙습니다 안식님
     */
    private ProductJoinDTO makeSignDTO(HttpServletRequest request) {
        EmployeeEntity user = (EmployeeEntity) request.getSession().getAttribute("login");
        Integer accId = null;
        if (request.getParameter("acc_id") != null) {
            accId = Integer.parseInt(request.getParameter("acc_id"));
        }
        Integer accCid = null;
        if (request.getParameter("acc_cid") != null) {
            accCid = Integer.parseInt(request.getParameter("acc_cid"));
        }
        Timestamp accDate = null;
        if (request.getParameter("acc_date") != null) {
            accDate = Timestamp.valueOf(request.getParameter("acc_date"));
        }
        Integer accBalance = null;
        if (request.getParameter("acc_balance") != null) {
            accBalance = Integer.parseInt(request.getParameter("acc_balance"));
        }
        String accPassword = request.getParameter("acc_password");
        Integer accPid = null;
        if (request.getParameter("acc_pid") != null) {
            accPid = Integer.parseInt(request.getParameter("acc_pid"));
        }
        String accPCategory = request.getParameter("acc_p_category");
        String accPName = request.getParameter("acc_pname");
        Double accInterestRate = null;
        if (request.getParameter("acc_interestrate") != null) {
            accInterestRate = Double.parseDouble(request.getParameter("acc_interestrate"));
        }
        Integer accCollateralValue = null;
        if (request.getParameter("acc_collateralvalue") != null) {
            accCollateralValue = Integer.parseInt(request.getParameter("acc_collateralvalue"));
        }
        Integer accInterestDay = null;
        if (request.getParameter("acc_interest_day") != null) {
            accInterestDay = Integer.parseInt(request.getParameter("acc_interest_day"));
        }
        Integer accContractMonth = null;
        if (request.getParameter("acc_contract_month") != null) {
            accContractMonth = Integer.parseInt(request.getParameter("acc_contract_month"));
        }
        Timestamp accMaturityDate = null;
        if (request.getParameter("acc_maturitydate") != null) {
            accMaturityDate = Timestamp.valueOf(request.getParameter("acc_maturitydate"));
        }
        Character accIsActive = null;
        if (request.getParameter("acc_isactive") != null && request.getParameter("acc_isactive").length() > 0) {
            accIsActive = request.getParameter("acc_isactive").charAt(0);
        }

        ProductJoinDTO productJoinDTO = ProductJoinDTO.builder()
                .acc_id(accId)
                .acc_cid(accCid)
                .acc_date(accDate)
                .acc_balance(accBalance)
                .acc_password(accPassword)
                .acc_pid(accPid)
                .acc_p_category(accPCategory)
                .acc_pname(accPName)
                .acc_interestrate(accInterestRate)
                .acc_collateralvalue(accCollateralValue)
                .acc_interest_day(accInterestDay)
                .acc_contract_month(accContractMonth)
                .acc_maturitydate(accMaturityDate)
                .acc_isactive(accIsActive)
                .build();

        System.out.println("IN makeSignDTO: " + productJoinDTO.toString());
        return productJoinDTO;
    }

    private String doSign(HttpServletRequest request) {
        String res = "가입에 실패했습니다";
        ProductJoinDTO productJoinDTO = makeSignDTO(request);
        System.out.println("doSign 진입");
        System.out.println("ProductJoinDTO = " + productJoinDTO.toString());
        res = "가입에 성공했습니다";
        return res;
    }


}
