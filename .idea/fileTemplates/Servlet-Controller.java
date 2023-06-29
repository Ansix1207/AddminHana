#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

#if ($JAVAEE_TYPE == "jakarta")
import jakarta.servlet.*;
import jakarta.servlet.http.*;
#else
import javax.servlet.*;
import javax.servlet.http.*;
#end
import java.io.IOException;
import java.io.PrintWriter;

public class ${Class_Name} extends HttpServlet {
    ServletContext context = null;
    BoardService boardService;
    ArticleVO articleVO;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        boardService = new BoardService();
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
        HttpSession session = request.getSession(false);
        String contextPath = request.getContextPath();
        String action = request.getPathInfo();
        String nextPage = "";
        System.out.println("action = " + action);
        try {
            List<ArticleVO> articleList = new ArrayList<>();
            if (action == null) {
                articleList = boardService.listArticles();
                request.setAttribute("articlesList", articleList);
                nextPage = "/board01/listArticles.jsp";
            } else if (action.equals("/listArticles.do")) {
                articleList = boardService.listArticles();
                request.setAttribute("articlesList", articleList);
                nextPage = "/board01/listArticles.jsp";
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
}
