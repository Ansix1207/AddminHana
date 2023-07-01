package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanJoinDAO;
import hana.teamfour.addminhana.DTO.ProductJoinDTO;
import hana.teamfour.addminhana.entity.AccountEntity;

public class LoanJoinService {
    private LoanJoinDAO loanJoinDAO;

    public LoanJoinService() {
        this.loanJoinDAO = new LoanJoinDAO();
    }

    //    LoanJoinService 객체를 생성할 때 LoanJoinDAO 객체가 초기화되어 NullPointerException을 방지할 수 있습니다.
    public boolean insertLoanJoin(ProductJoinDTO productJoinDTO) {
        AccountEntity accountEntity = convertToAccountEntity(productJoinDTO);
        boolean isSuccess = loanJoinDAO.insertAccount(accountEntity);

        return isSuccess;
    }

    private AccountEntity convertToAccountEntity(ProductJoinDTO productJoinDTO) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAcc_id(productJoinDTO.getAcc_id());
        accountEntity.setAcc_cid(productJoinDTO.getAcc_cid());
        accountEntity.setAcc_date(productJoinDTO.getAcc_date());
        accountEntity.setAcc_balance(productJoinDTO.getAcc_balance());
        accountEntity.setAcc_password(productJoinDTO.getAcc_password());

        accountEntity.setAcc_pid(productJoinDTO.getAcc_pid());
        accountEntity.setAcc_p_category(productJoinDTO.getAcc_p_category());
        accountEntity.setAcc_pname(productJoinDTO.getAcc_pname());
        accountEntity.setAcc_interestrate(productJoinDTO.getAcc_interestrate());
        accountEntity.setAcc_collateralvalue(productJoinDTO.getAcc_collateralvalue());

        accountEntity.setAcc_interest_day(productJoinDTO.getAcc_interest_day());
        accountEntity.setAcc_contract_month(productJoinDTO.getAcc_contract_month());
        accountEntity.setAcc_maturitydate(productJoinDTO.getAcc_maturitydate());
        accountEntity.setAcc_isactive(productJoinDTO.getAcc_isactive());
        System.out.println(accountEntity);
        return accountEntity;
    }
}