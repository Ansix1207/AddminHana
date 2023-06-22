package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.EmployeeEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;

    public EmployeeDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EmployeeEntity login(String id, String pw) {
        Connection conn = null; //변수선언 DB와 연결
        PreparedStatement ps = null; //SQL문 담당
        ResultSet rs = null; //검색 결과를 담을 것
        EmployeeEntity employee = null;
        try {
            conn = dataFactory.getConnection();


            String sql = "SELECT e_id, e_name, e_password  FROM admin_hana.employee WHERE e_id = ? AND e_password = ?";
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
