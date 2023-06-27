package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.SavingsAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class SavingsBalanceService {
    private final SavingsAccountDAO savingsAccountDao;
    private Integer id;

    public SavingsBalanceService(Integer id) {
        this.savingsAccountDao = new SavingsAccountDAO();
        this.id = id;
    }

    public Integer[] getSavingsBalance() {
        System.out.println("SavingsBalanceService 로드 성공");
        Integer[] savingsBalance = {0, 0};
        ArrayList<AccountEntity> accountEntity = savingsAccountDao.getSavingsAccListById(id);
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("자유적금")) {
                savingsBalance[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                savingsBalance[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        return savingsBalance;
    }
}