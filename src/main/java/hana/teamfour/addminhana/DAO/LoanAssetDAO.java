package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountInfo;
import hana.teamfour.addminhana.entity.AssetInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanAssetDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "jyp", "1234");
        return con;
    }

    public ArrayList<AssetInfo> getLoanAsset() {
        ArrayList<AssetInfo> list = new ArrayList<>();

        try {
            conn = getConnection();

            String sql = "select ass_loan ";
            sql += "from asset ";
            sql += "WHERE C_ID = 37";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("LoanAssetDAO 로드 성공");

            while (rs.next()) {
                AssetInfo assetInfo = new AssetInfo();
                assetInfo.setAss_loan(rs.getInt(1));
                list.add(assetInfo);
            }

            conn.close();
            ps.close();
            rs.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
