package pl.sda;

import pl.sda.users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TwitterServlet extends HttpServlet {


    private static final String AUTHOR = "Author";
    private static final String DEFAULT_AUTHOR = "Anonim";
    private List<Message> messages;
    private static String NEW_MESSAGE = "message";

    @Override
    public void init() throws ServletException {
        messages = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1250");

        PrintWriter out = resp.getWriter();

        out.println("<h1> Wiadomości: </h1>");

        messages.forEach(message-> showMessage(out, message));
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newMessage = req.getParameter(NEW_MESSAGE);
        User author = Optional.ofNullable(req.getAttribute(UserFilter.USER_ATRIBUTE))
                .filter(o -> o instanceof User)
                .map(o -> (User) o)
                .orElse(null);

        if(newMessage != null) {
            Message message = new Message(newMessage, author, LocalDateTime.now());
            messages.add(message);
        }

        doGet(req, resp);
    }
    private void showMessage(PrintWriter out, Message message) {
        out.println("<h3>" +message.getMessage() + "</h3>");

    }
}
