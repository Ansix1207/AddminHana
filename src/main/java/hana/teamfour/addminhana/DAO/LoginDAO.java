package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.EmployeeEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection
                ("jdbc:oracle:thin:@//localhost:1521/xe", "system", "1234");
        return con;
    }

    public EmployeeEntity login(String id, String pw) {
        Connection conn = null; //변수선언 DB와 연결
        PreparedStatement ps = null; //SQL문 담당
        ResultSet rs = null; //검색 결과를 담을 것
        EmployeeEntity employee = null;
        try {
            conn = getConnection();

            String sql = "SELECT e_id, e_name, e_password  FROM employee WHERE e_name = ? AND e_password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, pw);

            rs = ps.executeQuery();

            while (rs.next()) {
                int e_id = rs.getInt("e_id");
                String e_name = rs.getString("e_name");
                String e_password = rs.getString("e_password");
                employee = new EmployeeEntity(e_id, e_password, e_name);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
}
