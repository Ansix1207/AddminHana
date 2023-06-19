package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanAssetDAO;
import hana.teamfour.addminhana.entity.AssetInfo;

import java.util.ArrayList;

public class LoanAssetService {
    private final LoanAssetDAO loanAssetDao;

    public LoanAssetService(LoanAssetDAO loanAssetDao) {
        this.loanAssetDao = loanAssetDao;
    }

    public ArrayList<AssetInfo> getLoanAsset() {
        System.out.println("LoanAssetService 로드 성공");
        return loanAssetDao.getLoanAsset();
    }
}
