package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    ServletContext context = null;
    CustomerService customerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        customerService = new CustomerService();
        System.out.println("프로필 컨트롤러");
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
        String nextPage = "/views/profile.jsp";
        try {
            // 로그인을 해서 /profile 의 경로로 컨트롤러를 타고 들어오면
            // 1. CustomerSummaryDTO 를 서비스단에서 가져와서 request에 setAttri 한다.
            CustomerSummaryDTO customerSummaryDTO = customerService.getCustomerSummaryDTOById(1);
            request.setAttribute("customerSummaryDTO", customerSummaryDTO);
            System.out.println("customerSummaryDTO = " + customerSummaryDTO);

            // 2. AssetSummary
            // 3. description
            // 4. recommendation
            // 5. calendar data 

//            List<ArticleVO> articleList = new ArrayList<>();
//            if (action == null) {
//                articleList = boardService.listArticles();
//                request.setAttribute("articlesList", articleList);
//                nextPage = "/board01/listArticles.jsp";
//            } else if (action.equals("/listArticles.do")) {
//                articleList = boardService.listArticles();
//                request.setAttribute("articlesList", articleList);
//                nextPage = "/board01/listArticles.jsp";
//            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
//            response.sendRedirect(nextPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
