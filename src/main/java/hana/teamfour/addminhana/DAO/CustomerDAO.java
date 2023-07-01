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

    public boolean checkDuplicateByRRN(String rrn) {
        String query = "SELECT * FROM customer WHERE C_RRN = ?";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            System.out.println("query = " + query);
            statement.setString(1, rrn);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.close();
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    public CustomerEntity findByRRN(String _rrn) {
        CustomerEntity customerEntity = null;
        String query = "SELECT * FROM customer WHERE C_RRN = ?";
        System.out.println("query = " + query);
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setString(1, _rrn);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Integer c_id = rs.getInt("c_id");
                    String c_name = rs.getString("c_name");
                    String c_rrn = rs.getString("c_rrn");
                    Character c_gender = rs.getString("c_gender").charAt(0);
                    String c_job = rs.getString("c_job");
                    String c_address = rs.getString("c_address");
                    String c_mobile = rs.getString("c_mobile");
                    String c_description = rs.getString("c_description");
                    Integer e_id = rs.getInt("e_id");
                    customerEntity = new CustomerEntity(c_id, c_name, c_rrn, c_gender, c_address, c_mobile, c_job, c_description, e_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerEntity;
    }

    //    View <-> Controller <-> service <-> dao <-> db
//    dto             dto        dto     entity
    public CustomerEntity insertCustomer(CustomerEntity customerEntity) throws SQLException {
        String query = "INSERT INTO CUSTOMER VALUES(customer_seq.nextval,?,?,?,?,?,?,?,?)";
        System.out.println("query = " + query);
        try (Connection connection = dataFactory.getConnection()) {
            connection.setAutoCommit(false); //트랜잭션 처리를 위한 AutoCommit off, 트랜잭션 시작
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customerEntity.getC_name());//String name
                statement.setString(2, customerEntity.getC_rrn());//String 주민번호 rrn
                statement.setString(3, String.valueOf(customerEntity.getC_gender()));//String  성별 gender (M,F)
                statement.setString(4, customerEntity.getC_address());//String 주소 address
                statement.setString(5, customerEntity.getC_mobile());//String 주소 mobile
                statement.setString(6, customerEntity.getC_job());//String 직업(공무원,직장인,전문직,사업자,일반) job
                statement.setString(7, customerEntity.getC_description());//String 설명 description
                statement.setInt(8, customerEntity.getE_id());//int 주소 e_id
                if (statement.executeUpdate() == 1) {
                    connection.commit();
                    connection.setAutoCommit(true);
                    return findByRRN(customerEntity.getC_rrn());
                }
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
                throw e;
            }
        }
        return new CustomerEntity();
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

    public boolean updateCustomerEntity(CustomerEntity customerEntity) {
        Integer c_id = customerEntity.getC_id();
        String c_name = customerEntity.getC_name();
        String c_rrn = customerEntity.getC_rrn();
        Character c_gender = customerEntity.getC_gender();
        String c_job = customerEntity.getC_job();
        String c_description = customerEntity.getC_description();
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
            if (statement.executeUpdate() == 1) {
                connection.commit();
            } else {
                connection.rollback();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
