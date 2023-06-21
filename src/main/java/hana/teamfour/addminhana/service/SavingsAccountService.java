package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.SavingsAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class SavingsAccountService {
    private final SavingsAccountDAO savingsAccountDao;

    public SavingsAccountService(SavingsAccountDAO savingsAccountDao) {
        this.savingsAccountDao = savingsAccountDao;
    }

    public ArrayList<AccountEntity> getSavingsInfoList() {
        System.out.println("SavingsAccountService 로드 성공");
        return savingsAccountDao.getSavingsInfoList();
    }
}
