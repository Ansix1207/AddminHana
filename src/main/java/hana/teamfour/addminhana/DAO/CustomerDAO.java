package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

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
        try {
            conn = dataFactory.getConnection();
            String query = "select * from customer where c_id = ? ";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, _c_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Integer c_id = rs.getInt("c_id");
                String c_name = rs.getString("c_name");
                String c_rrn = rs.getString("c_rrn");
                Character c_gender = rs.getString("c_gender").charAt(0);
                String c_address = rs.getString("c_address");
                String c_mobile = rs.getString("c_mobile");
                String c_job = rs.getString("c_job");
                String c_description = rs.getString("c_description");
                Integer e_id = rs.getInt("e_id");
                customerEntity = new CustomerEntity(c_id, c_name, c_rrn, c_gender, c_address, c_mobile, c_job, c_description, e_id);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
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
        try {
            conn = dataFactory.getConnection();
            String query = "update customer set c_name=?, c_rrn=?," +
                    " c_gender=?, c_job=?, c_description=? " +
                    " where c_id=? ";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, c_name);
            pstmt.setString(2, c_rrn);
            pstmt.setString(3, c_gender.toString());
            pstmt.setString(4, c_job);
            pstmt.setString(5, c_description);
            pstmt.setInt(6, c_id);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
