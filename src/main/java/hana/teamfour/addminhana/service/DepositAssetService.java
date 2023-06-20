package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.DepositAssetDAO;
import hana.teamfour.addminhana.entity.AssetEntity;

public class DepositAssetService {
    private final DepositAssetDAO depositAssetDao;

    public DepositAssetService(DepositAssetDAO depositAssetDao) {
        this.depositAssetDao = depositAssetDao;
    }

    public AssetEntity getDepositAsset() {
        System.out.println("DepositAssetService 로드 성공");
        return depositAssetDao.getDepositAsset();
    }
}
