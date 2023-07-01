package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.AccountDAO;
import hana.teamfour.addminhana.DTO.AccountDTO;
import hana.teamfour.addminhana.DTO.AccountSummaryDTO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AccountService {
    private final AccountDAO accountDao;
    private Integer id;
    private String category;

    public AccountService(Integer id, String category) {
        this.accountDao = new AccountDAO();
        this.id = id;
        this.category = category;
    }

    public AccountService(Integer id) {
        this.accountDao = new AccountDAO();
        this.id = id;
    }

    public ArrayList<AccountDTO> getAccList() {
        ArrayList<AccountEntity> accountEntities = accountDao.getAccListById(id, category);
        return setAccList(accountEntities);
    }

    private ArrayList<AccountDTO> setAccList(ArrayList<AccountEntity> accountEntities) {
        ArrayList<AccountDTO> accountDTO = new ArrayList<>();
        for (AccountEntity accountEntity : accountEntities) {
            DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
            String acc_balance = decimalFormat.format(accountEntity.getAcc_balance());
            String acc_pname = accountEntity.getAcc_pname();
            Double acc_interestrate = accountEntity.getAcc_interestrate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String acc_maturitydate = dateFormat.format(accountEntity.getAcc_maturitydate());
            accountDTO.add(new AccountDTO(acc_balance, acc_pname, acc_interestrate, acc_maturitydate));
        }

        return accountDTO;
    }

    //출금할때 비밀번호로 성공적으로 인증하면 통장 정보(통장 타입(보통예금 통장만), 통장 이름) 을 보여주기 위한 서비스
    public AccountSummaryDTO getAccSummary(String type) {
        AccountSummaryDTO responseAccountSummaryDTO = new AccountSummaryDTO();
        //계좌 id 와 타입(출금인지 입금인지로 조회한다.
        AccountEntity result = accountDao.getAccountNameAndCategoryByAccId(id, type);
        if (result.getAcc_id() != -999) {
            //계좌번호, 상품 타입, 상품 이름을 넣어서 반환.
            responseAccountSummaryDTO.setAcc_pname(result.getAcc_pname());
            responseAccountSummaryDTO.setAcc_p_category(result.getAcc_p_category());
            responseAccountSummaryDTO.setAcc_id(id);
        } else {
            responseAccountSummaryDTO.setAcc_id(result.getAcc_id());
        }
        return responseAccountSummaryDTO;
    }
}
