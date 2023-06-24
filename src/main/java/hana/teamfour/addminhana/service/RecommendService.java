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
        System.out.println("RecommendService 로드 성공");
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
        System.out.println("가입된 상품 id 추가");
    }

    public ArrayList<ProductEntity> getRecByJob(String productType) {
        String job = customerSummaryDTO.getC_job();
        ArrayList<ProductEntity> recProducts = recByJobDAO.getRecProduct(userId, productType, job);
        ArrayList<ProductEntity> resultProducts = new ArrayList<>();
        for (ProductEntity product : recProducts) {
            if (!joinedIdSet.contains(product.getP_id())) {
                joinedIdSet.add(product.getP_id());
                resultProducts.add(product);
            }
        }
        System.out.println("Job 추천 상품 추가");
        return resultProducts;
    }

    public ArrayList<ProductEntity> getRecByGender(String productType) {
        Character gender = customerSummaryDTO.getC_gender();
        ArrayList<ProductEntity> recProducts = recByGenderDAO.getRecProduct(userId, productType, gender);
        ArrayList<ProductEntity> resultProducts = new ArrayList<>();
        for (ProductEntity product : recProducts) {
            if (!joinedIdSet.contains(product.getP_id())) {
                joinedIdSet.add(product.getP_id());
                resultProducts.add(product);
            }
        }
        System.out.println("gender 추천 상품 추가");
        return resultProducts;
    }

    public ArrayList<ProductEntity> getRecByAge(String productType) {
        Integer age = customerSummaryDTO.getC_age();
        Integer ageRange = ((int) age / 10) * 10;
        ArrayList<ProductEntity> recProducts = recByAgeDAO.getRecProduct(userId, productType, ageRange);
        ArrayList<ProductEntity> resultProducts = new ArrayList<>();
        for (ProductEntity product : recProducts) {
            if (!joinedIdSet.contains(product.getP_id())) {
                joinedIdSet.add(product.getP_id());
                resultProducts.add(product);
            }
        }
        System.out.println("Age 추천 상품 추가");
        return resultProducts;
    }
}