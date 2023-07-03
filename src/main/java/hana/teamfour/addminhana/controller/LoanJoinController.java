package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.AccountDTO;
import hana.teamfour.addminhana.DTO.CustomerSessionDTO;
import hana.teamfour.addminhana.DTO.ProductDTO;
import hana.teamfour.addminhana.service.LoanJoinService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/customer/loanjoin")
public class LoanJoinController extends HttpServlet {
    private LoanJoinService loanJoinService;

    public LoanJoinController() {
        this.loanJoinService = new LoanJoinService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession(false);

        CustomerSessionDTO customerSessionDTO = (CustomerSessionDTO) session.getAttribute("customerSession");
        Integer pid = Integer.valueOf(request.getParameter("pid"));

        ProductDTO productDTO = loanJoinService.getProductDTO(pid);

        request.setAttribute("customerSessionDTO", customerSessionDTO);
        request.setAttribute("productDTO", productDTO);

        dispatcher = request.getRequestDispatcher("/views/loanJoin.jsp");
        dispatcher.forward(request, response);
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

                LocalDateTime currentDateTime = LocalDateTime.now();

                Integer acc_id = Integer.valueOf(request.getParameter("ACC_ID"));
                Integer acc_cid = Integer.valueOf(request.getParameter("ACC_CID"));
                Timestamp acc_date = Timestamp.valueOf(currentDateTime);
                Integer acc_balance = Integer.valueOf(request.getParameter("ACC_BALANCE"));
                String acc_password = request.getParameter("ACC_PASSWORD");
                Integer acc_pid = Integer.valueOf(request.getParameter("ACC_PID"));
                String acc_p_category = request.getParameter("ACC_P_CATEGORY");
                String acc_pname = request.getParameter("ACC_P_NAME");
                Double acc_interestrate = Double.valueOf(request.getParameter("ACC_INTERESTRATE").replace("%", ""));
                Integer acc_collateralvalue = Integer.valueOf((int) (Double.valueOf(acc_balance) * Double.valueOf((request.getParameter("ACC_COLLATERALVALUE")))));
                Integer acc_interest_day = 1;
                Integer acc_contract_month = Integer.valueOf(request.getParameter("ACC_P_Month").replace("개월", ""));
                Timestamp acc_maturitydate = java.sql.Timestamp.valueOf(currentDateTime.plusMonths(acc_contract_month));
                Character acc_isactive = 'Y';

                AccountDTO accountDTO = new AccountDTO(acc_id, acc_cid, acc_date, acc_balance, acc_password, acc_pid, acc_p_category, acc_pname, acc_interestrate, acc_collateralvalue, acc_interest_day, acc_contract_month, acc_maturitydate, acc_isactive);

                boolean isSuccess = loanJoinService.insertLoanJoin(accountDTO);
                System.out.println("Controller 결과: " + isSuccess);
                String res;
                if (!isSuccess) {
                    res = "계좌 가입에 실패했습니다";
                } else {
                    res = "금융 상품이 등록되었습니다";
                }
                request.setAttribute("message", res);
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("<script>alert('" + res + ".'); window.location.href='" + request.getContextPath() + "';</script>");
                out.println("</body>");
                out.println("</html>");
            default:
        }
    }
}
