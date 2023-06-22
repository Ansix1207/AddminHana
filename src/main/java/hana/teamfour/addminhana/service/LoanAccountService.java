package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class LoanAccountService {
    private final LoanAccountDAO loanAccountDao;

    public LoanAccountService(LoanAccountDAO loanAccountDao) {
        this.loanAccountDao = loanAccountDao;
    }

    public ArrayList<AccountEntity> getLoanInfoList() {
        System.out.println("LoanAccountService 로드 성공");
        return loanAccountDao.getLoanInfoList();
    }
}
