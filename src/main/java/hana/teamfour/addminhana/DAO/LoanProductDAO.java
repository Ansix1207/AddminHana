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


    public LoanProductDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ProductEntity> getLoanProductList(String query, int page) {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        try {
            conn = dataFactory.getConnection();
            String sql = "select p_name, p_description, p_interestrate " +
                    "FROM (SELECT rownum AS num, p.*" +
                    "      FROM (SELECT *" +
                    "            FROM admin_hana.product" +
                    "            ) p)"
                    + "WHERE num BETWEEN ? AND ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1 + (page - 1) * 5);
            pstmt.setInt(2, page * 5);
            ResultSet rs = pstmt.executeQuery();
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

    public ArrayList<ProductEntity> getSearchLoanProductList(String query, int page) {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        try {
            conn = dataFactory.getConnection();
            String sql = "SELECT p_name, p_description, p_interestrate " +
                    "FROM (SELECT rownum AS num, p.* " +
                    "      FROM (SELECT * " +
                    "            FROM admin_hana.product " +
                    "            WHERE p_description LIKE ? or p_name LIKE ? ) p) " +
                    "WHERE num BETWEEN ? AND ?";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql); /* ?를 채우는것 */
            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");
            pstmt.setInt(3, 1 + (page - 1) * 5);
            pstmt.setInt(4, page * 5);
            ResultSet rs = pstmt.executeQuery();
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
