package mk.finki.ukim.wp.lab.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*public class BalloonFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object chosenColor = request.getSession().getAttribute("chosenColor");

        String path = request.getServletPath();

    if (!"/balloons".equals(path)  && chosenColor==null) {
            response.sendRedirect("/balloons");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }



    }

    @Override
    public void destroy() {

    }
}*/

