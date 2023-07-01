package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerDTO;
import hana.teamfour.addminhana.DTO.PaginationDTO;
import hana.teamfour.addminhana.service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/customerList")
public class CustomerListController extends HttpServlet {
    CustomerService customerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        customerService = new CustomerService();
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
        String nextPage = "views/customerList.jsp";
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        String search = request.getParameter("search");
        String orderBy = request.getParameter("orderBy");
        String ordering = request.getParameter("ordering");
        List<CustomerDTO> customerList = null;
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
            orderBy = "c_id";
        }
        if (ordering == null || ordering == "") {
            ordering = "asc";
        }

        try {
            Integer pageNum = Integer.parseInt(page);
            Integer sizeNum = Integer.parseInt(size);

            PaginationDTO paginationDTO = new PaginationDTO(pageNum, sizeNum, search, orderBy, ordering);
            customerList = customerService.getCustomerListWithPagination(paginationDTO);
            Integer customerCount = customerService.getCountRow();

            request.setAttribute("customerList", customerList);
            request.setAttribute("customerCount", customerCount);

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
