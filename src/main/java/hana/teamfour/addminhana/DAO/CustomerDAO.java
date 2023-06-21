package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.CustomerEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    private static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection
                ("jdbc:oracle:thin:@//localhost:1521/xe", "admin_hana", "1234");
        return con;
    }

    public CustomerEntity findById(Integer _c_id) {
        CustomerEntity customerEntity = null;
        try {
            conn = getConnection();
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
}
