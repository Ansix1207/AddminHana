package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class LoanRateService {
    private final LoanAccountDAO loanAccountDao;

    public LoanRateService(LoanAccountDAO loanAccountDao) {
        this.loanAccountDao = loanAccountDao;
    }

    public Integer[] getLoanRate() {
        System.out.println("LoanRateService 로드 성공");
        Double[] dbl_loanRate = {0.0, 0.0};
        ArrayList<AccountEntity> accountEntity = loanAccountDao.getLoanInfoList();
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("신용대출")) {
                dbl_loanRate[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                dbl_loanRate[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        Double totalBalance = dbl_loanRate[0] + dbl_loanRate[1];
        if (totalBalance == 0) return new Integer[] {0, 0};

        Integer[] loanRate = {0, 0};
        loanRate[0] = Math.toIntExact(Math.round(dbl_loanRate[0] / totalBalance * 100));
        loanRate[1] = Math.toIntExact(Math.round(dbl_loanRate[1] / totalBalance * 100));

        return loanRate;
    }
}
