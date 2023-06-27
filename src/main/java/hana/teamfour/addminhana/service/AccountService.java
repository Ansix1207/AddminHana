package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.AccountDAO;
import hana.teamfour.addminhana.DTO.AccountDTO;
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

    public ArrayList<AccountDTO> getAccList() {
        System.out.println(category + " AccountService 로드 성공");
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
            accountDTO.add(new AccountDTO(acc_balance,acc_pname, acc_interestrate, acc_maturitydate));
        }

        return accountDTO;
    }
}
