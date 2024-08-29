package cesar.gmca.todolistspring.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FilterTaskAuth implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)

            throws IOException, ServletException {
        // execute action
        System.out.println("Here, mate");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
