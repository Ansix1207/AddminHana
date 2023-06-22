package hana.teamfour.addminhana.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    ServletContext context;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Pre-processing code goes here
        request.setCharacterEncoding("utf-8");
        // Pass the request and response to the next filter or servlet in the chain
        chain.doFilter(request, response);
        // Post-processing code goes here
    }
}
