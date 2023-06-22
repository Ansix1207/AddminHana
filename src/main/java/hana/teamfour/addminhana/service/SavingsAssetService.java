package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.SavingsAssetDAO;
import hana.teamfour.addminhana.entity.AssetEntity;

public class SavingsAssetService {
    private final SavingsAssetDAO savingsAssetDao;

    public SavingsAssetService(SavingsAssetDAO savingsAssetDao) {
        this.savingsAssetDao = savingsAssetDao;
    }

    public AssetEntity getSavingsAsset() {
        System.out.println("SavingsAssetService 로드 성공");
        return savingsAssetDao.getSavingsAsset();
    }
}
