package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.DepositAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class DepositRateService {
    private final DepositAccountDAO depositAccountDao;

    public DepositRateService(DepositAccountDAO depositAccountDao) {
        this.depositAccountDao = depositAccountDao;
    }

    public Integer[] getDepositRate() {
        System.out.println("DepositRateService 로드 성공");
        Double[] dbl_depositRate = {0.0, 0.0};
        ArrayList<AccountEntity> accountEntity = depositAccountDao.getDepositInfoList();
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("보통예금")) {
                dbl_depositRate[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                dbl_depositRate[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        Double totalBalance = dbl_depositRate[0] + dbl_depositRate[1];
        if (totalBalance == 0) return new Integer[] {0, 0};

        Integer[] depositRate = {0, 0};
        depositRate[0] = Math.toIntExact(Math.round(dbl_depositRate[0] / totalBalance * 100));
        depositRate[1] = Math.toIntExact(Math.round(dbl_depositRate[1] / totalBalance * 100));

        return depositRate;
    }
}
