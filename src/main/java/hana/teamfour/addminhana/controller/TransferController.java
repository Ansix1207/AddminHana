package hana.teamfour.addminhana.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ansik/*")
public class TransferController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String context = request.getContextPath();
        String uri = request.getRequestURI().replace(context, "");

        System.out.println("context: " + context);
        System.out.println("uri: " + uri);
        System.out.println(uri.equals("/ansik/transfer"));

        request.setAttribute("title", "계좌이체");

        if (uri.equals("/ansik/transfer")) {
            request.setAttribute("title", "계좌이체");
        } else if (uri.equals("/ansik/withdraw")) {
            request.setAttribute("title", "출금");
        } else {
            request.setAttribute("title", "입금");
        }
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/ansik.jsp");
        dispatcher.forward(request, response);
    }
}
