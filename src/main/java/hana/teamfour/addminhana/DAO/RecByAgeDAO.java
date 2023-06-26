package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RecByAgeDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

    public RecByAgeDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductEntity> getRecProduct(Integer id, String productType, Integer ageRange) {
        ArrayList<ProductEntity> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataFactory.getConnection();

            String sql = "select pro.p_id, pro.p_name, pro.p_interestrate, pro.p_limit " +
                    "from customer cus, account acc, product pro " +
                    "where cus.c_id = acc.acc_cid and acc.acc_pid = pro.p_id and acc.acc_cid <> ? and pro.p_isactive = 'Y' " +
                    "and REGEXP_SUBSTR(substr(pro.p_category, 3, 2), ?) = substr(pro.p_category, 3, 2) " +
                    "and FLOOR((TRUNC(MONTHS_BETWEEN(sysdate, to_date(to_char(TO_DATE(substr(cus.c_rrn, 1, 6), 'rrmmdd'), 'yyyy-mm-dd')))/12))/10)*10 = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, productType);   // productType을 지정하지 않는 경우 '..'
            ps.setInt(3, ageRange);


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
