package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSignDTO;
import hana.teamfour.addminhana.DTO.EmployeeDTO;
import hana.teamfour.addminhana.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sign")
public class SignController extends HttpServlet {
    CustomerService customerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        customerService = new CustomerService();
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
                dispatcher = request.getRequestDispatcher("./views/sign.jsp");
                dispatcher.forward(request, response);
                break;
            case "POST":
                System.out.println("POST 진입 & valid_rrn == " + request.getParameter("valid_rrn"));
                if (!checkEmptyOrNull(request, "valid_rrn")) {
                    String res = doValidRRN(request);
                    request.setAttribute("message", res);

                    dispatcher = request.getRequestDispatcher("./views/sign.jsp");
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
        String res = "중복된 주민등록 번호입니다.";
        System.out.println(request.getParameter("valid_rrn") + " <- 이 번호로 조회 시도");
        if (!customerService.checkDuplicateByRRN(request.getParameter("valid_rrn"))) {
            System.out.println("중복되지 않았음.");
            res = "중복되지 않았습니다";
        }
        request.setAttribute("customerSignDTO", makeSignDTO(request));
        return res;
    }

    private String doSign(HttpServletRequest request) {
        String res = "가입에 실패했습니다";
        CustomerSignDTO customerSignDTO = makeSignDTO(request);
        System.out.println("doSign 진입");
        System.out.println("customerSignDTO = " + customerSignDTO.toString());
        if (customerService.signCustomer(customerSignDTO)) {
            System.out.println("In doSign : 성공");
            res = "가입에 성공했습니다";
        }
        return res;
    }

    /**
     * request로 전달받은 값들을 통해 CustomerSignDTO를 만들고 반환합니다.
     */
    private CustomerSignDTO makeSignDTO(HttpServletRequest request) {
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("login");
        CustomerSignDTO customerSignDTO = CustomerSignDTO.builder()
                .c_name(request.getParameter("c_name"))
                .c_rrn(request.getParameter("c_rrn1") + "-" + request.getParameter("c_rrn2"))
                .c_gender(customerService.getGenderFromRRN(request.getParameter("c_rrn1") + "-" +
                        request.getParameter("c_rrn2")))
                .c_address(request.getParameter("c_address1") + " " +
                        request.getParameter("c_address2"))
                .c_mobile(request.getParameter("c_mobile"))
                .c_job(request.getParameter("c_job"))
                .c_description(request.getParameter("c_description"))
                .e_id(user.getE_id())
                .build();
        System.out.println("IN makeSignDTO : " + customerSignDTO.toString());
        return customerSignDTO;
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
