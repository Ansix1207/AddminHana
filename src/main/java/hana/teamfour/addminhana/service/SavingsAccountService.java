package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.SavingsAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class SavingsAccountService {
    private final SavingsAccountDAO savingsAccountDao;
    private Integer id;

    public SavingsAccountService(Integer id) {

        this.savingsAccountDao = new SavingsAccountDAO();
        this.id = id;
    }

    public ArrayList<AccountEntity> getSavingsAccList() {
        System.out.println("SavingsAccountService 로드 성공");
        return savingsAccountDao.getSavingsAccListById(id);
    }
}