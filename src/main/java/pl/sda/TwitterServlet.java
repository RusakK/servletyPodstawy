package pl.sda;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TwitterServlet extends HttpServlet {


    private List<String> messages;
    private static String NEW_MESSAGE = "message";

    @Override
    public void init() throws ServletException {
        messages = new ArrayList<>();
        messages.add("wiadomośc 1");
        messages.add("wiadomośc 2");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1250");

        PrintWriter out = resp.getWriter();

        out.println("<h1> Wiadomości: </h1>");

        messages.forEach(message-> showMessage(out, message));
    }

    private void showMessage(PrintWriter out, String message) {
        out.println("<h3>" + message + "</h3>");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String newMessage = req.getParameter(NEW_MESSAGE);

        if(newMessage != null) {
            messages.add(newMessage);
        }

        doGet(req, resp);
    }
}
