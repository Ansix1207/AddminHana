package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.DepositAccountDAO;
import hana.teamfour.addminhana.entity.AccountEntity;

import java.util.ArrayList;

public class DepositAccountService {
    private final DepositAccountDAO depositAccountDao;
    private Integer id;

    public DepositAccountService(Integer id) {
        this.depositAccountDao = new DepositAccountDAO();
        this.id = id;
    }

    public ArrayList<AccountEntity> getDepositAccList() {
        System.out.println("DepositAccountService 로드 성공");
        return depositAccountDao.getDepositAccListById(id);
    }
}
