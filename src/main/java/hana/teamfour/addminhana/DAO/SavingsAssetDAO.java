package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AssetEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SavingsAssetDAO {
    private DataSource dataFactory;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public SavingsAssetDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssetEntity getSavingsAsset(Integer id) {
        AssetEntity assetEntity = new AssetEntity();

        try {
            conn = dataFactory.getConnection();

            String sql = "select ass_savings " +
                    "from asset " +
                    "WHERE C_ID = ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            System.out.println("SavingsAssetDAO 로드 성공");

            while (rs.next()) {
                assetEntity.setAss_savings(rs.getInt(1));
            }

            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assetEntity;
    }
}
