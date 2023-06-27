package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AssetEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanAssetDAO {
    private DataSource dataFactory;

    public LoanAssetDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssetEntity getLoanAssetById(Integer id) {
        AssetEntity assetEntity = new AssetEntity();
        String query = "select ass_loan " +
                "from asset " +
                "WHERE C_ID = ?";

        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    assetEntity.setAss_loan(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assetEntity;
    }
}