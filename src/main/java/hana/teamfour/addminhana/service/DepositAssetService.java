package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.DepositAssetDAO;
import hana.teamfour.addminhana.entity.AssetEntity;

public class DepositAssetService {
    private final DepositAssetDAO depositAssetDao;
    private Integer id;

    public DepositAssetService(Integer id) {
        this.depositAssetDao = new DepositAssetDAO();
        this.id = id;
    }

    public AssetEntity getDepositAsset() {
        System.out.println("DepositAssetService 로드 성공");
        return depositAssetDao.getDepositAssetById(id);
    }
}
