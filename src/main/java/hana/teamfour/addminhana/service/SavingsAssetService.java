package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.SavingsAssetDAO;
import hana.teamfour.addminhana.entity.AssetEntity;

public class SavingsAssetService {
    private final SavingsAssetDAO savingsAssetDao;
    private Integer id;

    public SavingsAssetService(Integer id) {

        this.savingsAssetDao = new SavingsAssetDAO();
        this.id = id;
    }

    public AssetEntity getSavingsAsset() {
        System.out.println("SavingsAssetService 로드 성공");
        return savingsAssetDao.getSavingsAssetById(id);
    }
}