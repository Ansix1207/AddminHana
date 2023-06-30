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
    String productType;
    HashSet<Integer> joinedIdSet = new HashSet<>();
    ArrayList<ProductEntity> notJoinedProducts;

    public RecommendService(Integer id, String productType) {
        this.userId = id;
        this.productType = productType;
        this.productDAO = new ProductDAO();
        this.recByAgeDAO = new RecByAgeDAO();
        this.recByGenderDAO = new RecByGenderDAO();
        this.recByJobDAO = new RecByJobDAO();

        CustomerService customerService = new CustomerService();
        customerSummaryDTO = customerService.getCustomerSummaryDTOById(userId);
        getJoinedProduct();
        initArrayList();
    }

    public void getJoinedProduct() {
        ArrayList<ProductEntity> joinedProducts = productDAO.findByID(userId, productType);
        for (ProductEntity product : joinedProducts) {
            joinedIdSet.add(product.getP_id());
        }
        System.out.println("가입된 상품 id 추가");
    }

    public ArrayList<ProductEntity> getRecByJob() {
        // 직업이 같은 손님이 가입한 상품
        String job = customerSummaryDTO.getC_job();
        ArrayList<ProductEntity> recProducts = recByJobDAO.getRecProduct(userId, productType, job);
        ArrayList<ProductEntity> resultProducts = getRecProducts(recProducts);
        System.out.println("Job 추천 상품 추가");
        return resultProducts;
    }

    public ArrayList<ProductEntity> getRecByGender() {
        // 성별이 같은 손님이 가입한 상품
        Character gender = customerSummaryDTO.getC_gender();
        ArrayList<ProductEntity> recProducts = recByGenderDAO.getRecProduct(userId, productType, gender);
        ArrayList<ProductEntity> resultProducts = getRecProducts(recProducts);
        System.out.println("Gender 추천 상품 추가");
        return resultProducts;
    }

    public ArrayList<ProductEntity> getRecByAge() {
        // 나이대가 같은 손님이 가입한 상품
        Integer age = customerSummaryDTO.getC_age();
        Integer ageRange = ((int) age / 10) * 10;
        ArrayList<ProductEntity> recProducts = recByAgeDAO.getRecProduct(userId, productType, ageRange);
        ArrayList<ProductEntity> resultProducts = getRecProducts(recProducts);
        System.out.println("Age 추천 상품 추가");
        return resultProducts;
    }

    public ArrayList<ProductEntity> getRecProducts(ArrayList<ProductEntity> recProducts) {
        int n = 2; // 추천 상품 개수

        ArrayList<ProductEntity> resultProducts = new ArrayList<>();
        for (ProductEntity product : recProducts) {
            if (resultProducts.size() >= n) break;
            if (!joinedIdSet.contains(product.getP_id())) {
                joinedIdSet.add(product.getP_id());
                resultProducts.add(product);
            }
        }

        // 추천 상품이 n개보다 적을 경우 그 외의 상품 추천
        for (ProductEntity product : notJoinedProducts) {
            if (resultProducts.size() >= n) break;
            if (!joinedIdSet.contains(product.getP_id())) {
                joinedIdSet.add(product.getP_id());
                resultProducts.add(product);
            }
        }

        return resultProducts;
    }

    public void initArrayList() {
        notJoinedProducts = productDAO.findNotJoined(userId, productType);
    }
}
