package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanProductDAO;
import hana.teamfour.addminhana.DTO.ProductDTO;
import hana.teamfour.addminhana.entity.ProductEntity;

import java.util.ArrayList;

public class LoanProductService {
    LoanProductDAO loanProductDAO = null;

    public LoanProductService() {
        loanProductDAO = new LoanProductDAO();
    }

    public ArrayList<ProductDTO> getProducts(int page) {
        ArrayList<ProductEntity> productEntities = loanProductDAO.getLoanProductList(page);
        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
        for (int i = 0; i < productEntities.size(); i++) {
            System.out.println(productEntities.get(i).getP_name());
            ProductDTO productDTO = new ProductDTO(productEntities.get(i));
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    public ArrayList<ProductDTO> getSearchLoanProductList(String query, int page) {
        ArrayList<ProductEntity> productEntities = loanProductDAO.getSearchLoanProductList(query, page);
        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
        for (int i = 0; i < productEntities.size(); i++) {
            ProductDTO productDTO = new ProductDTO(productEntities.get(i));
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }
}
