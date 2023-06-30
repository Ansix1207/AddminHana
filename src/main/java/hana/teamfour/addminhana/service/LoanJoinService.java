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

    public void insertLoanJoin(ProductJoinDTO productJoinDTO) {
        AccountEntity accountEntity = convertToAccountEntity(productJoinDTO);
        loanJoinDAO.insertAccount(accountEntity);
    }


    /*
    insertLoanJoin 메서드는 ProductJoinDTO 객체를 받아와서 해당 정보를 AccountEntity로 변환한 후,
    loanJoinDAO를 사용하여 데이터베이스에 해당 AccountEntity를 삽입하는 역할을 합니다.
    
    convertToAccountEntity 메서드를 호출하여 ProductJoinDTO를 AccountEntity로 변환합니다.
    변환된 AccountEntity를 loanJoinDAO.insertAccount 메서드에 전달하여 데이터베이스에 삽입합니다.
    loanJoinDAO.insertAccount 메서드 내부에서는 데이터베이스 연결을 수행하고,
     PreparedStatement를 사용하여 SQL INSERT 문을 실행하여 데이터를 삽입합니다.
     */
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

        // 다른 속성들은 ProductJoinDTO를 기반으로 설정합니다
        System.out.println(accountEntity);
        return accountEntity;
    }

//    public ArrayList<LoanJoinDAO> getJoins() {
//        LoanJoinDAO loanjoinDAO = new LoanJoinDAO(); // LoanProduct 객체 생성
//        ArrayList<AccountEntity> accountEntities = loanjoinDAO.insertJoin();
//        ArrayList<ProductJoinDTO> JoinDTOs = new ArrayList<ProductJoinDTO>();
//        for (int i = 0; i < accountEntities.size(); i++) {
//            // productEntities.get(i) -> type은? ProductEntity
//            System.out.println(accountEntities.get(i).getAcc_id());
//            ProductJoinDTO productJoinDTO = new ProductJoinDTO(accountEntities.get(i)); //entities에서 하나 꺼내와서 DTO로 만든다.
//            JoinDTOs.add(productJoinDTO);
//        }
//        System.out.println("DTOS" + JoinDTOs);
//        return JoinDTOs;
//    }

}
//    public ArrayList<ProductJoinDTO> getProducts() {
////        ArrayList<ProductJoinDTO>를 받을 getProducts() 는 무엇이냐 하면
//        LoanJoinDAO loanJoinDAO = new LoanJoinDAO(); // LoanProduct 객체 생성
//        ArrayList<ProductEntity> productEntities = loanProductDAO.insertAccount(page);
//        ArrayList<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
//        for (int i = 0; i < productEntities.size(); i++) {
//            // productEntities.get(i) -> type은? ProductEntity
//            System.out.println(productEntities.get(i).getP_name());
//            ProductDTO productDTO = new ProductDTO(productEntities.get(i)); //entities에서 하나 꺼내와서 DTO로 만든다.
//            productDTOs.add(productDTO);
//        }
//        return productDTOs;
//    }
//
//    private ProductJoinSummaryDTO setProductJoinSummary(AccountEntity accountEntity) {
//        Integer acc_id = accountEntity.getAcc_id();
//        Integer acc_cid = accountEntity.getAcc_cid();
//        Timestamp acc_date = accountEntity.getAcc_date();
//        Integer acc_balance = accountEntity.getAcc_balance();
//        String acc_password = accountEntity.getAcc_password();
//        Integer acc_pid = accountEntity.getAcc_pid();
//        String acc_p_category = accountEntity.getAcc_p_category();
//        String acc_pname = accountEntity.getAcc_pname();
//        Double acc_interestrate = accountEntity.getAcc_interestrate();
//        Integer acc_collateralvalue = accountEntity.getAcc_collateralvalue();
//        Integer acc_interest_day = accountEntity.getAcc_interest_day();
//        Integer acc_contract_month = accountEntity.getAcc_contract_month();
//        Timestamp acc_maturitydate = accountEntity.getAcc_maturitydate();
//        Character acc_isactive = accountEntity.getAcc_isactive();
//        System.out.println("Service");
//
//
//        return new ProductJoinSummaryDTO(acc_id, acc_cid, acc_date, acc_balance, acc_password, acc_pid,
//                acc_p_category, acc_pname, acc_interestrate, acc_collateralvalue, acc_interest_day, acc_contract_month
//                , acc_maturitydate, acc_isactive);
//    }