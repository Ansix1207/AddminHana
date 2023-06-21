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
            String sql = "select p_name, p_limit, p_interestrate from admin_hana.product where p_category in (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "신용대출");
            pstmt.setString(2, "담보대출");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setP_name(rs.getString(1));
                productEntity.setP_limit(rs.getInt(2));
                productEntity.setP_interestrate(rs.getDouble(3));
                productEntityList.add(productEntity);
            }
            conn.close();
            pstmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productEntityList;
    }
}
