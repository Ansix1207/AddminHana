package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.EmployeeEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class EmployeeDAO {
    private DataSource dataFactory;
    private static EmployeeDAO instance = new EmployeeDAO();

    private EmployeeDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EmployeeDAO getInstance() {
        return instance;
    }

    public EmployeeEntity login(String id, String pw) {
        EmployeeEntity employee = null;
        String query = "{CALL select_by_eid(?, ?, ?, ?)}";

        try (Connection connection = dataFactory.getConnection();
             CallableStatement statement = connection.prepareCall(query)) {

            statement.setInt(1, Integer.parseInt(id));
            statement.setString(2, pw); // p_password
            statement.registerOutParameter(3, Types.VARCHAR);
            statement.registerOutParameter(4, Types.NUMERIC);
            statement.execute();

            // 결과 값 가져오기
            String e_name = statement.getString(3);
            int success = statement.getInt(4);

            // success 값으로 성공 여부 확인 후 결과 사용 (1: 성공)
            if (success == 1) {
                employee = new EmployeeEntity(Integer.valueOf(id), pw, e_name);
            } else {
                throw new Exception("해당하는 데이터를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

}
