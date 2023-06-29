package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanProductDAO;
import hana.teamfour.addminhana.DTO.ProductDTO;
import hana.teamfour.addminhana.entity.ProductEntity;

import java.util.ArrayList;

public class LoanProductService {
    /*
    private final LoanProductDAO loanProductDao;

    public LoanProductService(LoanProductDAO loanProductDao) {
        this.loanProductDao = loanProductDao;
    }
     */

    public LoanProductService() {
    }

    // Service에서 해야할 일
    // Entity <-> DTO 변환
    public ArrayList<ProductDTO> getProducts(int page) {
        LoanProductDAO loanProductDAO = new LoanProductDAO(); // LoanProduct 객체 생성
        ArrayList<ProductEntity> productEntities = loanProductDAO.getLoanProductList(page);

        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        for (int i = 0; i < productEntities.size(); i++) {
            // productEntities.get(i) -> type은? ProductEntity
            System.out.println(productEntities.get(i).getP_name());
            ProductDTO productDTO = new ProductDTO(productEntities.get(i)); //entities에서 하나 꺼내와서 DTO로 만든다.
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public ArrayList<ProductDTO> getSearchLoanProductList(String query, int page) {
        LoanProductDAO loanProductDAO = new LoanProductDAO(); // LoanProduct 객체 생성
        ArrayList<ProductEntity> productEntities = loanProductDAO.getSearchLoanProductList(query, page);

        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

        for (int i = 0; i < productEntities.size(); i++) {
            // productEntities.get(i) -> type은? ProductEntity
            ProductDTO productDTO = new ProductDTO(productEntities.get(i)); //entities에서 하나 꺼내와서 DTO로 만든다.
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    /*
    public ProductDTO getLoanProductList(int page) {
        return loanProductDao.getLoanProductList(page);
    }


    public ProductDTO getSearchLoanProductList(String field, String query, int page) {
        ProductDTO productEntityList = new ArrayList<>();
        return loanProductDao.getSearchLoanProductList(query, page);
    }

    public int getProductCount(String query) {
        return loanProductDao.getProductCount(query);
    }

    public Map<String, Integer> getAccountCountByCategory() {
        return loanProductDao.getAccountCountByCategory();
    }
    
    */
}
