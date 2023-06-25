package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanProductDAO;
import hana.teamfour.addminhana.entity.ProductEntity;

import java.util.ArrayList;

public class LoanProductService {
    private final LoanProductDAO loanProductDao;

    public LoanProductService(LoanProductDAO loanProductDao) {
        this.loanProductDao = loanProductDao;
    }

//    public ArrayList<ProductEntity> getLoanProductList() {
//        return loanProductDao.getLoanProductList();
////        원래는 return loanProductDao.getLoanProductList()
//    }


//    여기서부터는 실험중


    public ArrayList<ProductEntity> getLoanProductList(String query, int page) {
        return loanProductDao.getLoanProductList(query, page);
    }

    public ArrayList<ProductEntity> getLoanProductList(String field, String query, int page) {
        return loanProductDao.getLoanProductList(query, page);
    }


    public ArrayList<ProductEntity> getSearchLoanProductList(String field, String query /*A*/, int page) {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        String sql = "select * from ("
                + "     select rownum num, product. * "
                + "      from product where p_name like ? )  " + "where num between ? and ?";
        return loanProductDao.getSearchLoanProductList(query, page);
    }


    public int getLoanProductCount(int id) {
        String sql = "select * from product where id=?";

        return 0;
    }

    //
    public int getLoanProductCount(String field, String query) {
        String sql = "select * from product where id=?";

        return 0;
    }


    public ProductEntity getLoanProduct(int p_id) {
        return null;

    }


    public ProductEntity getNextProduct(int p_id) {
        String sql = " select * from product " +
                "where p_id = ( " +
                "        select p_id from product " +
                " where p_id > (select p_id from product where p_id=5) " +
                " and rownum = 1)";

        return null;
    }

    public ProductEntity getPrevProduct(int p_id) {
        String sql = " select * from product " +
                "where p_id = ( " +
                "        select p_id from (select p_id from product order by p_id DESC) " +
                " where p_id < (select p_id from product where p_id=6) " +
                " and rownum = 1)";
        return null;
    }


}
