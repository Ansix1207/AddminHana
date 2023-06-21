package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.EmployeeEntity;

import java.sql.*;

public class EmployeeDAO {

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection
                ("jdbc:oracle:thin:@//localhost:1521/xe", "admin_hana", "1234");
        return conn;
    }

    public EmployeeEntity login(String id, String pw) {
        Connection conn = null; //변수선언 DB와 연결
        CallableStatement cs = null; //SQL 프로시저 담당
        ResultSet rs = null; //검색 결과를 담을 것
        EmployeeEntity employee = null;
        try {
            conn = getConnection();

            String sql = "{CALL select_by_eid(?, ?, ?, ?)}";
            cs = conn.prepareCall(sql);

            cs.setInt(1, Integer.parseInt(id)); // 예시로 100을 사용
            cs.setString(2, pw); // p_password
            cs.registerOutParameter(3, Types.VARCHAR); // p_name
            cs.registerOutParameter(4, Types.NUMERIC); // p_success
            cs.execute();

            // 결과 값 가져오기
            String e_name = cs.getString(3);
            int success = cs.getInt(4);

            // success 값으로 성공 여부 확인 후 결과 사용 (1: 성공)
            if (success == 1) {
                employee = new EmployeeEntity(Integer.valueOf(id), pw, e_name);
            } else {
                throw new Exception("해당하는 데이터를 찾을 수 없습니다.");
            }

            conn.close();
            cs.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }
}
