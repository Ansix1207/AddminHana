package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanProductDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

    //<<<<<<< HEAD
//    public static Connection getConnection() throws Exception {
//        Class.forName("oracle.jdbc.OracleDriver");
//        Connection con = DriverManager.getConnection
//                ("jdbc:oracle:thin:@//localhost:1521/xe", "admin_hana", "1234");
//        return con;
//=======
    public LoanProductDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
//>>>>>>> 03f763b0955bb7491242cb837b3b23287514f0c2
    }

    public ArrayList<ProductEntity> getLoanProductList() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        try {
//<<<<<<< HEAD
//            conn = getConnection();
//
//            String sql = "select p_name, p_description, p_interestrate from admin_hana.product";
////            String sql = "select p_name, p_description, p_interestrate from product where p_category in (?, ?)";
//
//            ps = conn.prepareStatement(sql);
////            ps.setString(1, "신용대출");
////            ps.setString(2, "담보대출");
//            rs = ps.executeQuery();
//
//=======
            conn = dataFactory.getConnection();
            String sql = "select p_name, p_description, p_interestrate from admin_hana.product where p_category in (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "신용대출");
            pstmt.setString(2, "담보대출");
            ResultSet rs = pstmt.executeQuery();
//>>>>>>> 03f763b0955bb7491242cb837b3b23287514f0c2
            while (rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setP_name(rs.getString(1));
                productEntity.setP_description(rs.getString(2));
                productEntity.setP_interestrate(rs.getDouble(3));
                productEntityList.add(productEntity);
            }
            conn.close();
            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("들렸다갑니다");
        System.out.println("productEntityList = " + productEntityList);
        return productEntityList;
    }
}
