package ua.training.controller.filters;

import ua.training.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        String roleRequired = "";
        if (path.contains("admin")) roleRequired = "admin";
        else if (path.contains("client")) roleRequired = "client";
        switch (roleRequired) {
            case "admin":
                if (user!=null&&(user.getRole() == User.ROLE.ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    servletResponse.getWriter().append("AccessDenied");
                    return;
                }
                break;
            case "client":
                if (user!=null&&(user.getRole() == User.ROLE.CLIENT || user.getRole() == User.ROLE.ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    servletResponse.getWriter().append("AccessDenied");
                    return;
                }
                break;
            default:
                filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}