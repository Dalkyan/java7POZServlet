package filters;

import domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/admin.jsp")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);

        HttpSession session = ((HttpServletRequest) req).getSession(false);
        User user = (User)session.getAttribute("user");

        if (session != null && user != null) {

            System.out.println("użytkownik" + user.getFirstName() + " posiada sesję.");
            chain.doFilter(req,resp);
        }else {
            System.err.println("Nie ma użytkownika lub sesji");
            ((HttpServletResponse)resp).sendRedirect("/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
