package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.PaginationDTO;
import hana.teamfour.addminhana.DTO.TransactionDTO;
import hana.teamfour.addminhana.service.TransactionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/transactionList")
public class TransactionListController extends HttpServlet {
    TransactionService transactionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        transactionService = new TransactionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String nextPage = "views/transactionList.jsp";
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        String search = request.getParameter("search");
        String orderBy = request.getParameter("orderBy");
        String ordering = request.getParameter("ordering");
        String t_accidStr = request.getParameter("t_accid");
        Integer t_accid = t_accidStr == null ? 1 : Integer.parseInt(t_accidStr);

        System.out.println("t_accid = " + t_accid);

        List<TransactionDTO> transactionDTOList = null;
        if (page == null || page == "") {
            page = "1";
        }
        if (size == null || size == "") {
            size = "10";
        }
        if (search == null || search == "") {
            search = "%";
        } else {
            search = "%" + search + "%";
        }
        if (orderBy == null || orderBy == "") {
            orderBy = "t_accid";
        }
        if (ordering == null || ordering == "") {
            ordering = "desc";
        }

        try {
            Integer pageNum = Integer.parseInt(page);
            Integer sizeNum = Integer.parseInt(size);

            PaginationDTO paginationDTO = new PaginationDTO(pageNum, sizeNum, search, orderBy, ordering);
            transactionDTOList = transactionService.getCustomerListWithPagination(t_accid, paginationDTO);
            Integer transactionCount = transactionService.getCountRow(t_accid);

            request.setAttribute("transactionList", transactionDTOList);
            request.setAttribute("transactionCount", transactionCount);

            RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
