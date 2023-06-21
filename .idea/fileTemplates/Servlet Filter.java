#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import javax.servlet.*;
import java.io.IOException;

public class ${NAME} implements Filter {
    ServletContext context;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code goes here (optional)
        context = filterConfig.getServletContext();
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Pre-processing code goes here

        // Pass the request and response to the next filter or servlet in the chain
        chain.doFilter(request, response);

        // Post-processing code goes here
    }

    @Override
    public void destroy() {
        // Cleanup code goes here (optional)
    }
}