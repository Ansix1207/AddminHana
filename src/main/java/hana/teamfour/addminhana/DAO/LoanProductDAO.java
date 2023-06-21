package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanProductDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    // 이 코드는 LoanProductDAO 클래스의 멤버 변수로 데이터베이스 연결과 관련된 객체들을 선언하고, 초기값으로 null을 설정합니다. 

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection
                ("jdbc:oracle:thin:@//localhost:1521/xe", "admin_hana", "1234");
        return con;
    }


    public ArrayList<ProductEntity> getLoanProductList() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();

        try {
            conn = getConnection();

            String sql = "select p_name, p_limit, p_interestrate from admin_hana.product";
//            String sql = "select p_name, p_limit, p_interestrate from product where p_category in (?, ?)";

            ps = conn.prepareStatement(sql);
//            ps.setString(1, "신용대출");
//            ps.setString(2, "담보대출");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setP_name(rs.getString(1));
                productEntity.setP_limit(rs.getInt(2));
                productEntity.setP_interestrate(rs.getDouble(3));
                productEntityList.add(productEntity);
            }
            System.out.println("productEntityList = " + productEntityList);
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("productEntityList = " + productEntityList);
        return productEntityList;
    }
}
