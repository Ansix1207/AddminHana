package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.ProductJoinDTO;
import hana.teamfour.addminhana.entity.EmployeeEntity;
import hana.teamfour.addminhana.service.LoanJoinService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet("/loanjoin")
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
                System.out.println("POST 진입 & valid_rrn == " + request.getParameter("valid_rrn"));
                if (!checkEmptyOrNull(request, "valid_rrn")) {
                    String res = doValidRRN(request);
                    request.setAttribute("message", res);
                    request.setAttribute("ACC_CID", request.getParameter("idNum"));
                    request.setAttribute("ACC_DATE", request.getParameter("today"));
                    request.setAttribute("ACC_P_CATEGORY", request.getParameter("productType"));
                    request.setAttribute("ACC_PNAME", request.getParameter("product"));
                    request.setAttribute("c_name", request.getParameter("customerName"));
                    request.setAttribute("c_job", request.getParameter("job"));
                    request.setAttribute("c_mobile", request.getParameter("mobile"));
                    request.setAttribute("c_description", request.getParameter("description"));
                    dispatcher = request.getRequestDispatcher("./views/loanJoin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    System.out.println("가입 시도");
                    String res = doSign(request);
                    request.setAttribute("message", res);
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<script>alert('" + res + ".'); window.location.href='" + request.getContextPath() + "';</script>");
                    out.println("</body>");
                    out.println("</html>");
                }
                System.out.println("POST 요청 처리 끝");
                break;
            default:
        }
    }

    private String doValidRRN(HttpServletRequest request) {
        String res = "중복된 회원 번호입니다.";
        System.out.println(request.getParameter("valid_rrn") + " <- 이 번호로 조회 시도");
        if (!loanJoinService.checkDuplicateByRRN(request.getParameter("valid_rrn"))) {
            System.out.println("중복되지 않았음.");
            res = "중복되지 않았습니다";
        }
        request.setAttribute("customerSignDTO", makeSignDTO(request));
        return res;
    }

    private String doSign(HttpServletRequest request) {
        String res = "가입에 실패했습니다";
        ProductJoinDTO productJoinDTO = makeSignDTO(request);
        System.out.println("doSign 진입");
        System.out.println("customerSignDTO = " + productJoinDTO.toString());
        if (loanJoinService.signCustomer(productJoinDTO)) {
            System.out.println("In doSign : 성공");
            res = "가입에 성공했습니다";
        }
        return res;
    }

    /**
     * request로 전달받은 값들을 통해 LoanServiceDTO를 만들고 반환합니다. 고맙습니다 안식님
     */
    private ProductJoinDTO makeSignDTO(HttpServletRequest request) {
        EmployeeEntity user = (EmployeeEntity) request.getSession().getAttribute("login");
        ProductJoinDTO productJoinDTO = ProductJoinDTO.builder()
                .ACC_CID(Integer.valueOf(request.getParameter("ACC_CID")))
                .ACC_DATE(Timestamp.valueOf(request.getParameter("ACC_DATE")))
                .ACC_P_CATEGORY(request.getParameter("ACC_P_CATEGORY"))
                .ACC_PNAME(request.getParameter("ACC_PNAME"))
                .c_name(request.getParameter("c_name"))
                .c_job(request.getParameter("c_job"))
                .c_mobile(request.getParameter("c_mobile"))
                .c_description(request.getParameter("c_description"))
                .build();
        System.out.println("IN makeSignDTO : " + productJoinDTO.toString());
        return productJoinDTO;
    }

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
