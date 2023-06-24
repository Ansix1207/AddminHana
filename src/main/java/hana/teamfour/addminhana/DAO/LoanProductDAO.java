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

    public ArrayList<ProductEntity> getLoanProductList() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        try {
            conn = dataFactory.getConnection();
            String sql = "select p_name, p_description, p_interestrate from admin_hana.product where p_category in (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "신용대출");
            pstmt.setString(2, "담보대출");
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


//    실험중입니다 

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

    public int getLoanProductCount(String field, String query) {
// 페이징이 되지않은 목록이 몇개인지 알아내는 함수
        int count = 0;

        String sql = "select count(p_id) count from (" + "     select rownum num, product. * "
                + "      from product )  "
                + "      from product where p_name like ? )  " + "where num between ? and ?";
//        String url = 

        try {
            conn = dataFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, "%" + query + "%");

            ResultSet rs = st.executeQuery();
            count = rs.getInt("count");
            conn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("들렸다갑니다");

        return count;
    }


    public ProductEntity getProduct(int p_id) {
        ProductEntity product = null;

        String sql = "select * from product where p_id = ?";

        try {
            conn = dataFactory.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, p_id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                product.setP_id(rs.getInt(1));
                product.setP_name(rs.getString(2));
                product.setP_description(rs.getString(3));
                product.setP_interestrate(rs.getDouble(4));

            }
            conn.close();
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("들렸다갑니다");

        return product;
    }


}
