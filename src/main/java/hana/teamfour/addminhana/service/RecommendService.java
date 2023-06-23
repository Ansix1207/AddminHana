package hana.teamfour.addminhana.service;


import hana.teamfour.addminhana.DAO.ProductDAO;
import hana.teamfour.addminhana.DAO.RecByAgeDAO;
import hana.teamfour.addminhana.DAO.RecByGenderDAO;
import hana.teamfour.addminhana.DAO.RecByJobDAO;
import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.ProductEntity;

import java.util.ArrayList;
import java.util.HashSet;

public class RecommendService {
    ProductDAO productDAO;
    RecByAgeDAO recByAgeDAO;
    RecByGenderDAO recByGenderDAO;
    RecByJobDAO recByJobDAO;
    CustomerSummaryDTO customerSummaryDTO;
    Integer userId;
    HashSet<Integer> joinedIdSet = new HashSet<>();

    public RecommendService(Integer id) {
        System.out.println("RecommendService 로드 완료");
        this.userId = id;
        this.productDAO = new ProductDAO();
        this.recByAgeDAO = new RecByAgeDAO();
        this.recByGenderDAO = new RecByGenderDAO();
        this.recByJobDAO = new RecByJobDAO();

        CustomerService customerService = new CustomerService();
        customerSummaryDTO = customerService.getCustomerSummaryDTOById(userId);
        getJoinedProduct();
    }

    public void getJoinedProduct() {
        ArrayList<ProductEntity> joinedProducts = productDAO.getProduct(userId);
        for (ProductEntity product : joinedProducts) {
            joinedIdSet.add(product.getP_id());
        }
    }

    public ArrayList<ProductEntity> getRecByJob() {
        String job = customerSummaryDTO.getC_job();
        ArrayList<ProductEntity> recProducts = recByJobDAO.getRecProduct(userId, job);
        for (ProductEntity product : recProducts) {
            if (!joinedIdSet.contains(product.getP_id())) {
                recProducts.add(product);
            }
        }
        return recProducts;
    }

    public ArrayList<ProductEntity> getRecByGender() {
        Character gender = customerSummaryDTO.getC_gender();
        ArrayList<ProductEntity> recProducts = recByGenderDAO.getRecProduct(userId, gender);
        for (ProductEntity product : recProducts) {
            if (!joinedIdSet.contains(product.getP_id())) {
                recProducts.add(product);
            }
        }
        return recProducts;
    }

    public ArrayList<ProductEntity> getRecByAge() {
        Integer age = customerSummaryDTO.getC_age();
        Integer ageRange = ((int) age / 10) * 10;
        ArrayList<ProductEntity> recProducts = recByAgeDAO.getRecProduct(userId, ageRange);
        for (ProductEntity product : recProducts) {
            if (!joinedIdSet.contains(product.getP_id())) {
                recProducts.add(product);
            }
        }
        return recProducts;
    }
}
