package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecByGenderDAO {
    private DataSource dataFactory;

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
        String query = "select pro.p_id, pro.p_name, pro.p_interestrate, pro.p_limit " +
                "from customer cus, account acc, product pro " +
                "where cus.c_id = acc.acc_cid and acc.acc_pid = pro.p_id and cus.c_gender = ? and acc.acc_cid <> ? and pro.p_isactive = 'Y' " +
                "and REGEXP_SUBSTR(substr(pro.p_category, 3, 2), ?) = substr(pro.p_category, 3, 2) " +
                "group by pro.p_id, pro.p_name, pro.p_interestrate, pro.p_limit " +
                "order by count(*) desc";

        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, String.valueOf(gender));
            statement.setInt(2, id);
            statement.setString(3, productType);   // productType을 지정하지 않는 경우 '..'

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setP_id(resultSet.getInt(1));
                    productEntity.setP_name(resultSet.getString(2));
                    productEntity.setP_interestrate(resultSet.getDouble(3));
                    productEntity.setP_limit(resultSet.getInt(4));
                    productList.add(productEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
