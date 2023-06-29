package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;
import oracle.jdbc.OracleTypes;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class RecByJobDAO {
    private DataSource dataFactory;

    public RecByJobDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductEntity> getRecProduct(Integer id, String productType, String job) {
        ArrayList<ProductEntity> productList = new ArrayList<>();
        String query = "{CALL select_rec_by_job(?, ?, ?, ?, ?, ?, ?, ?)}";
        
        try (Connection connection = dataFactory.getConnection();
             CallableStatement statement = connection.prepareCall(query)){

            statement.setInt(1, id);
            statement.setString(2, job);
            statement.setString(3, productType);   // productType을 지정하지 않는 경우 '..'
            statement.registerOutParameter(4, Types.INTEGER); // p_id
            statement.registerOutParameter(5, Types.VARCHAR); // p_name
            statement.registerOutParameter(6, Types.DOUBLE);  // p_interestrate
            statement.registerOutParameter(7, Types.INTEGER); // p_limit
            statement.registerOutParameter(8, OracleTypes.CURSOR);
            statement.execute();

            try (ResultSet resultSet = (ResultSet) statement.getObject(8)) {
                while (resultSet.next()) {
                    ProductEntity productEntity = new ProductEntity();
                    productEntity.setP_id(resultSet.getInt("p_id"));
                    productEntity.setP_name(resultSet.getString("p_name"));
                    productEntity.setP_interestrate(resultSet.getDouble("p_interestrate"));
                    productEntity.setP_limit(resultSet.getInt("p_limit"));
                    productList.add(productEntity);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
