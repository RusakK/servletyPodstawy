package pl.sda;

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
import java.util.function.Consumer;

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

    private void showMessage(PrintWriter out, Message message) {
        out.println("<h3>" +message.getMessage() + "</h3>");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newMessage = req.getParameter(NEW_MESSAGE);
        String autor = Optional.ofNullable(req.getParameter(AUTHOR)).orElse(DEFAULT_AUTHOR);

        if(newMessage != null) {
            Message message = new Message(newMessage, autor, LocalDateTime.now());
            messages.add(message);
        }

        doGet(req, resp);
    }
}
