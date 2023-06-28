package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.service.LoanJoinService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer/loanjoin")
public class LoanJoinController extends HttpServlet {
    private LoanJoinService loanJoinService;

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
                dispatcher = request.getRequestDispatcher("./views/loanJoin.jsp");
                dispatcher.forward(request, response);
                break;
            case "POST":
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
                dispatcher = request.getRequestDispatcher("./views/loanJoin.jsp");
                dispatcher.forward(request, response);
                System.out.println("POST 요청 처리 끝");
                break;
            default:
        }
    }


    /**
     * request로 전달받은 값들을 통해 LoanServiceDTO를 만들고 반환합니다. 고맙습니다 안식님
     */


    private boolean checkEmptyOrNull(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (param == null || param.trim().isEmpty() || param == "null" || param == "NULL") {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
