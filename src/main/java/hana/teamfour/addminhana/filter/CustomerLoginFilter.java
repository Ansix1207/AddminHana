package hana.teamfour.addminhana.filter;

import hana.teamfour.addminhana.DTO.CustomerSessionDTO;

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
        String loginURI = request.getContextPath() + "/customer/profile";

        CustomerSessionDTO customerSession = (CustomerSessionDTO) session.getAttribute("customerSession");
        boolean loggedIn = session != null && customerSession != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
