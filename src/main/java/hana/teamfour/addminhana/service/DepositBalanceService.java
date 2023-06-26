package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.DepositAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class DepositBalanceService {
    private final DepositAccountDAO depositAccountDao;
    private Integer id;

    public DepositBalanceService(Integer id) {
        this.depositAccountDao = new DepositAccountDAO();
        this.id = id;
    }

    public Integer[] getDepositBalance() {
        System.out.println("DepositBalanceService 로드 성공");
        Integer[] depositBalance = {0, 0};
        ArrayList<AccountEntity> accountEntity = depositAccountDao.getDepositInfoList(id);
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("보통예금")) {
                depositBalance[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                depositBalance[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        return depositBalance;
    }
}
