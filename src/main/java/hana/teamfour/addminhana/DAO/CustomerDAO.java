package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private DataSource dataFactory;

    public CustomerDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerEntity findById(Integer _c_id) {
        CustomerEntity customerEntity = null;
        String query = "select * from customer where c_id = ? ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setInt(1, _c_id);
            System.out.println("query = " + query);
            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve data from the result set
                    Integer c_id = resultSet.getInt("c_id");
                    String c_name = resultSet.getString("c_name");
                    String c_rrn = resultSet.getString("c_rrn");
                    Character c_gender = resultSet.getString("c_gender").charAt(0);
                    String c_address = resultSet.getString("c_address");
                    String c_mobile = resultSet.getString("c_mobile");
                    String c_job = resultSet.getString("c_job");
                    String c_description = resultSet.getString("c_description");
                    Integer e_id = resultSet.getInt("e_id");
                    customerEntity = new CustomerEntity(c_id, c_name, c_rrn, c_gender, c_address, c_mobile, c_job, c_description, e_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerEntity;
    }

    public CustomerEntity findByRrn(String _c_rrn) {
        CustomerEntity customerEntity = null;
        String query = "select * from customer where c_rrn = ? ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, _c_rrn);
            System.out.println("query = " + query);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer c_id = resultSet.getInt("c_id");
                    String c_name = resultSet.getString("c_name");
                    String c_rrn = resultSet.getString("c_rrn");
                    Character c_gender = resultSet.getString("c_gender").charAt(0);
                    String c_address = resultSet.getString("c_address");
                    String c_mobile = resultSet.getString("c_mobile");
                    String c_job = resultSet.getString("c_job");
                    String c_description = resultSet.getString("c_description");
                    Integer e_id = resultSet.getInt("e_id");
                    customerEntity = new CustomerEntity(c_id, c_name, c_rrn, c_gender, c_address, c_mobile, c_job, c_description, e_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerEntity;
    }

    public boolean updateCustomerSummary(CustomerSummaryDTO customerSummaryDTO) {
        Integer c_id = customerSummaryDTO.getC_id();
        String c_name = customerSummaryDTO.getC_name();
        String c_rrn = customerSummaryDTO.getC_rrn();
        Character c_gender = customerSummaryDTO.getC_gender();
        String c_job = customerSummaryDTO.getC_job();
        String c_description = customerSummaryDTO.getC_description();
        String query = "update customer set c_name=?, c_rrn=?," +
                " c_gender=?, c_job=?, c_description=? " +
                " where c_id=? ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            System.out.println("query = " + query);
            statement.setString(1, c_name);
            statement.setString(2, c_rrn);
            statement.setString(3, c_gender.toString());
            statement.setString(4, c_job);
            statement.setString(5, c_description);
            statement.setInt(6, c_id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
