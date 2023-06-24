package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RecByGenderDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

    public RecByGenderDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductEntity> getRecProduct(Integer id, String productType, Character gender) {
        ArrayList<ProductEntity> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataFactory.getConnection();

            String sql = "select pro.p_id, pro.p_name, pro.p_interestrate, pro.p_limit " +
                    "from customer cus, account acc, product pro " +
                    "where cus.c_id = acc.acc_cid and acc.acc_pid = pro.p_id and cus.c_gender = ? and acc.acc_cid <> ? and substr(pro.p_category, 3, 2) = ? and pro.p_isactive = 'Y' " +
                    "group by pro.p_id, pro.p_name, pro.p_interestrate, pro.p_limit " +
                    "order by count(*) desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(gender));
            ps.setInt(2, id);
            ps.setString(3, productType);

            rs = ps.executeQuery();

            while (rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setP_id(rs.getInt(1));
                productEntity.setP_name(rs.getString(2));
                productEntity.setP_interestrate(rs.getDouble(3));
                productEntity.setP_limit(rs.getInt(4));
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