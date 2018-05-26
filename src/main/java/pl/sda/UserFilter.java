package pl.sda;

import pl.sda.users.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class UserFilter implements Filter {

    private Map<String, User> userMap;
    public static final String USER_ATRIBUTE = "loggedUser";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<User> users = new ArrayList<>();
        users.add(new User("Użytkownik 1", "user", "tajne"));
        users.add(new User("Użytkownik 2", "admin", "inne"));

        userMap = users.stream()
                .collect(Collectors.toMap(User::getLogin, Function.identity()));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;


        // oczekujy ze ktos wyslal login
        String login  = httpServletRequest.getParameter("login");


        // sprawdzamy czy taki user istnieje
        if(login != null && userMap.containsKey(login)){
            servletRequest.setAttribute(USER_ATRIBUTE, userMap.get(login));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() { }
}
