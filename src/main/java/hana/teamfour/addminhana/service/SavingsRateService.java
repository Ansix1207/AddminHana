package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.SavingsAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class SavingsRateService {
    private final SavingsAccountDAO savingsAccountDao;

    public SavingsRateService(SavingsAccountDAO savingsAccountDao) {
        this.savingsAccountDao = savingsAccountDao;
    }

    public Integer[] getSavingsRate() {
        System.out.println("SavingsRateService 로드 성공");
        Double[] dbl_savingsRate = {0.0, 0.0};
        ArrayList<AccountEntity> accountEntity = savingsAccountDao.getSavingsInfoList();
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("자유적금")) {
                dbl_savingsRate[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                dbl_savingsRate[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        Double totalBalance = dbl_savingsRate[0] + dbl_savingsRate[1];
        if (totalBalance == 0) return new Integer[] {0, 0};

        Integer[] savingsRate = {0, 0};
        savingsRate[0] = Math.toIntExact(Math.round(dbl_savingsRate[0] / totalBalance * 100));
        savingsRate[1] = Math.toIntExact(Math.round(dbl_savingsRate[1] / totalBalance * 100));

        return savingsRate;
    }
}
