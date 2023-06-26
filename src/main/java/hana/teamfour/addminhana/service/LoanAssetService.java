package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.LoanAssetDAO;
import hana.teamfour.addminhana.entity.AssetEntity;

public class LoanAssetService {
    private final LoanAssetDAO loanAssetDao;
    private Integer id;

    public LoanAssetService(Integer id) {

        this.loanAssetDao = new LoanAssetDAO();
        this.id = id;
    }

    public AssetEntity getLoanAsset() {
        System.out.println("LoanAssetService 로드 성공");
        return loanAssetDao.getLoanAsset(id);
    }
}
