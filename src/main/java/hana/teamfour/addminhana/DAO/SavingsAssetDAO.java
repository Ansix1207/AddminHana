package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AssetEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SavingsAssetDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "admin_hana", "1234");
        return con;
    }

    public AssetEntity getSavingsAsset() {
        AssetEntity assetEntity = new AssetEntity();

        try {
            conn = getConnection();

            String sql = "select ass_savings ";
            sql += "from asset ";
            sql += "WHERE C_ID = 37";

            ps = conn.prepareStatement(sql);
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
