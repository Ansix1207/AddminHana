package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class LoanAccountService {
    private final LoanAccountDAO loanAccountDao;
    private Integer id;

    public LoanAccountService(Integer id) {

        this.loanAccountDao = new LoanAccountDAO();
        this.id = id;
    }

    public ArrayList<AccountEntity> getLoanAccList() {
        System.out.println("LoanAccountService 로드 성공");
        return loanAccountDao.getLoanAccListById(id);
    }
}
