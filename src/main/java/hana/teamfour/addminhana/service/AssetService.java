package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.AccountDAO;
import hana.teamfour.addminhana.DAO.AssetDAO;
import hana.teamfour.addminhana.DTO.AssetDTO;
import hana.teamfour.addminhana.DTO.AssetSumDTO;
import hana.teamfour.addminhana.entity.AccountEntity;
import hana.teamfour.addminhana.entity.AssetEntity;

import java.util.ArrayList;
import java.util.List;

public class AssetService {
    private final AssetDAO assetDAO;
    private final AccountDAO accountDAO;
    private Integer id;
    private String category;

    public AssetService() {
        this.assetDAO = new AssetDAO();
        this.accountDAO = new AccountDAO();
    }

    public AssetService(Integer id, String category) {
        this.assetDAO = new AssetDAO();
        this.accountDAO = new AccountDAO();
        this.id = id;
        this.category = category;
    }

    public AssetDTO getAsset() {
        System.out.println(category + " AssetService 로드 성공");
        AssetEntity assetEntity = assetDAO.getAssetById(id);
        ArrayList<AccountEntity> accountEntities = accountDAO.getAccListById(id, category);
        return setAsset(assetEntity, accountEntities);
    }

    private AssetDTO setAsset(AssetEntity assetEntity, ArrayList<AccountEntity> accountEntities) {
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setAss_deposit(assetEntity.getAss_deposit());
        assetDTO.setAss_loan(assetEntity.getAss_loan());
        assetDTO.setAss_savings(assetEntity.getAss_savings());
        assetDTO.setBalance_sum(getBalanceSum(accountEntities));
        return assetDTO;
    }

    private Integer[] getBalanceSum(ArrayList<AccountEntity> accountEntities) {
        Integer[] balanceSum = {0, 0};  // 카테고리별 잔액 합
        String first_category = category == "예금" ? category == "적금" ? "보통예금" : "자유적금" : "신용대출";
        for (AccountEntity accountEntity : accountEntities) {
            if (accountEntity.getAcc_p_category().equals(first_category)) {
                balanceSum[0] += Math.abs(accountEntity.getAcc_balance());
            } else {
                balanceSum[1] += Math.abs(accountEntity.getAcc_balance());
            }
        }

        return balanceSum;
    }

    public void refreshAssetTable(Integer acc_cid) {
        List<AssetSumDTO> assetSumDTOList = accountDAO.getSumOfAccBalance(acc_cid);
        if (assetSumDTOList == null) {
            return;
        }
        Integer assDeposit = 0;
        Integer assSavings = 0;
        Integer assLoan = 0;
        for (AssetSumDTO assetSumDTO : assetSumDTOList) {
            Integer balanceSum = assetSumDTO.getBalanceSum();
            String productCategory = assetSumDTO.getAccProductCategory();
            productCategory = productCategory.substring(2);
            switch (productCategory) {
                case "예금":
                    assDeposit += balanceSum;
                    break;
                case "적금":
                    assSavings += balanceSum;
                    break;
                case "대출":
                    assLoan += balanceSum;
                    break;
            }
        }
        AssetDTO assetDTO = new AssetDTO(assDeposit, assSavings, assLoan, null);
        assetDAO.updateAssetTableById(acc_cid, assetDTO);
    }
}
