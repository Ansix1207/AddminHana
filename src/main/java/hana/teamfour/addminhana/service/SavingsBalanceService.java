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
        Double[] dbl_savingsBalance = {0.0, 0.0};
        ArrayList<AccountEntity> accountEntity = savingsAccountDao.getSavingsInfoList(id);
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("자유적금")) {
                dbl_savingsBalance[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                dbl_savingsBalance[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        Double totalBalance = dbl_savingsBalance[0] + dbl_savingsBalance[1];
        if (totalBalance == 0) return new Integer[] {0, 0};

        Integer[] savingsBalance = {0, 0};
        savingsBalance[0] = Math.toIntExact(Math.round(dbl_savingsBalance[0] / totalBalance * 100));
        savingsBalance[1] = Math.toIntExact(Math.round(dbl_savingsBalance[1] / totalBalance * 100));

        return savingsBalance;
    }
}
