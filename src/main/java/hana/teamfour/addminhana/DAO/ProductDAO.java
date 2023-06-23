package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

    public ProductDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductEntity> getProduct(Integer id) {
        ArrayList<ProductEntity> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataFactory.getConnection();

            String sql = "select pro.p_id " +
                    "from account acc, product pro " +
                    "where acc.acc_pid = pro.p_id and acc.acc_cid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setP_id(rs.getInt(1));
                productList.add(productEntity);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
