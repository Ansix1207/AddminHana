package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AssetInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoanAssetDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "jyp", "1234");
        return con;
    }

    public AssetInfo getLoanAsset() {
        AssetInfo assetInfo = new AssetInfo();

        try {
            conn = getConnection();

            String sql = "select ass_loan ";
            sql += "from asset ";
            sql += "WHERE C_ID = 37";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("LoanAssetDAO 로드 성공");

            assetInfo.setAss_loan(rs.getInt(1));

            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assetInfo;
    }
}
