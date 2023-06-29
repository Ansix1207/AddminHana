package hana.teamfour.addminhana.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CustomerLoginFilter", urlPatterns = "/customer/*")
public class CustomerLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("customerSession") != null;

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/main");
        }
    }
}
