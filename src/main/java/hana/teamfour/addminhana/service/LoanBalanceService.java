package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class LoanBalanceService {
    private final LoanAccountDAO loanAccountDao;
    private Integer id;

    public LoanBalanceService(Integer id) {

        this.loanAccountDao = new LoanAccountDAO();
        this.id = id;
    }

    public Integer[] getLoanBalance() {
        System.out.println("LoanBalanceService 로드 성공");
        Integer[] loanBalance = {0, 0};
        ArrayList<AccountEntity> accountEntity = loanAccountDao.getLoanAccListById(id);
        for (int i=0; i<accountEntity.size(); i++) {
            if (accountEntity.get(i).getAcc_p_category().equals("신용대출")) {
                loanBalance[0] += Math.abs(accountEntity.get(i).getAcc_balance());
            } else {
                loanBalance[1] += Math.abs(accountEntity.get(i).getAcc_balance());
            }
        }

        return loanBalance;
    }
}