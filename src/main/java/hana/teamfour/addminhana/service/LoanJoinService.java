package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanJoinDAO;
import hana.teamfour.addminhana.DTO.ProductDTO;
import hana.teamfour.addminhana.DTO.ProductJoinDTO;
import hana.teamfour.addminhana.DTO.ProductJoinSummaryDTO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.ProductEntity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class LoanJoinService {

    private final LoanJoinDAO loanJoinDAO;
    private Integer id;

    public LoanJoinService(Integer id) {
        loanJoinDAO = new LoanJoinDAO();
    }

    public ArrayList<ProductJoinDTO> getProducts() {
//        ArrayList<ProductJoinDTO>를 받을 getProducts() 는 무엇이냐 하면
        LoanJoinDAO loanJoinDAO = new LoanJoinDAO(); // LoanProduct 객체 생성
        ArrayList<ProductEntity> productEntities = loanProductDAO.insertAccount(page);
        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
        for (int i = 0; i < productEntities.size(); i++) {
            // productEntities.get(i) -> type은? ProductEntity
            System.out.println(productEntities.get(i).getP_name());
            ProductDTO productDTO = new ProductDTO(productEntities.get(i)); //entities에서 하나 꺼내와서 DTO로 만든다.
            productDTOs.add(productDTO);
        }
        return productDTOs;
    }

    private ProductJoinSummaryDTO setProductJoinSummary(AccountEntity accountEntity) {
        Integer acc_id = accountEntity.getAcc_id();
        Integer acc_cid = accountEntity.getAcc_cid();
        Timestamp acc_date = accountEntity.getAcc_date();
        Integer acc_balance = accountEntity.getAcc_balance();
        String acc_password = accountEntity.getAcc_password();
        Integer acc_pid = accountEntity.getAcc_pid();
        String acc_p_category = accountEntity.getAcc_p_category();
        String acc_pname = accountEntity.getAcc_pname();
        Double acc_interestrate = accountEntity.getAcc_interestrate();
        Integer acc_collateralvalue = accountEntity.getAcc_collateralvalue();
        Integer acc_interest_day = accountEntity.getAcc_interest_day();
        Integer acc_contract_month = accountEntity.getAcc_contract_month();
        Timestamp acc_maturitydate = accountEntity.getAcc_maturitydate();
        Character acc_isactive = accountEntity.getAcc_isactive();
        System.out.println("Service");


        return new ProductJoinSummaryDTO(acc_id, acc_cid, acc_date, acc_balance, acc_password, acc_pid,
                acc_p_category, acc_pname, acc_interestrate, acc_collateralvalue, acc_interest_day, acc_contract_month
                , acc_maturitydate, acc_isactive);
    }
}
