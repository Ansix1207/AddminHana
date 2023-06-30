package hana.teamfour.addminhana.controller;

import hana.teamfour.addminhana.DTO.CustomerSessionDTO;
import hana.teamfour.addminhana.service.AssetService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer/asset-update")
public class AssetController extends HttpServlet {
    ServletContext context = null;
    AssetService assetService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        assetService = new AssetService();
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
        HttpSession session = request.getSession(false);
        String action = request.getPathInfo();
        String contextPath = request.getContextPath();
        String nextPage = "/customer/profile";
        CustomerSessionDTO customerSessionDTO = (CustomerSessionDTO) session.getAttribute("customerSession");
        Integer customerId = customerSessionDTO.getC_id();
        try {
            assetService.refreshAssetTable(customerId);

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
