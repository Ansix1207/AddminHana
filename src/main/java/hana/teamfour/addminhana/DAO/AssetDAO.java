package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.DTO.AssetDTO;
import hana.teamfour.addminhana.entity.AssetEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class AssetDAO {
    private DataSource dataFactory;

    public AssetDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssetEntity getAssetById(Integer id) {
        AssetEntity assetEntity = null;
        String query = "select * " +
                "from asset " +
                "WHERE C_ID = ?";

        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer ass_id = resultSet.getInt("ass_id");
                    Integer c_id = resultSet.getInt("c_id");
                    Integer ass_stock = resultSet.getInt("ass_stock");
                    Integer ass_bond = resultSet.getInt("ass_bond");
                    Integer ass_realestate = resultSet.getInt("ass_realestate");
                    Integer ass_deposit = resultSet.getInt("ass_deposit");
                    Integer ass_savings = resultSet.getInt("ass_savings");
                    Integer ass_loan = resultSet.getInt("ass_loan");
                    Integer ass_total = resultSet.getInt("ass_total");
                    Timestamp ass_lastupdate = resultSet.getTimestamp("ass_lastupdate");
                    assetEntity = new AssetEntity(ass_id, c_id, ass_stock, ass_bond, ass_realestate, ass_deposit, ass_savings, ass_loan, ass_total, ass_lastupdate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetEntity;
    }

    public void updateAssetTableById(Integer c_id, AssetDTO assetDTO) {
        String query = "update asset " +
                " set " +
                "     ass_deposit = ? " +
                "     ass_savings = ? " +
                "     ass_loan = ? " +
                " where " +
                "     c_id = ? ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, c_id);
            statement.setInt(2, assetDTO.getAss_deposit());
            statement.setInt(3, assetDTO.getAss_savings());
            statement.setInt(4, assetDTO.getAss_loan());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
