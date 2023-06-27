package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanProductDAO;
import hana.teamfour.addminhana.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Map;

public class LoanProductService {
    private final LoanProductDAO loanProductDao;

    public LoanProductService(LoanProductDAO loanProductDao) {
        this.loanProductDao = loanProductDao;
    }

    public ArrayList<ProductEntity> getLoanProductList(int page) {
        return loanProductDao.getLoanProductList(page);
    }

    public ArrayList<ProductEntity> getSearchLoanProductList(String field, String query, int page) {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        return loanProductDao.getSearchLoanProductList(query, page);
    }

    public int getProductCount(String query) {
        return loanProductDao.getProductCount(query);
    }

    public Map<String, Integer> getAccountCountByCategory() {
        return loanProductDao.getAccountCountByCategory();
    }
}
