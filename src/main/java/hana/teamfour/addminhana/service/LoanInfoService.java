package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanInfoDAO;
import hana.teamfour.addminhana.entity.AccountInfo;

import java.util.ArrayList;

public class LoanInfoService {
    private final LoanInfoDAO loanInfoDao;

    public LoanInfoService(LoanInfoDAO loanInfoDao) {
        this.loanInfoDao = loanInfoDao;
    }

    public ArrayList<AccountInfo> getLoanInfoList() {
        System.out.println("LoanInfoService 로드 성공");
        return loanInfoDao.getLoanInfoList();
    }
}
