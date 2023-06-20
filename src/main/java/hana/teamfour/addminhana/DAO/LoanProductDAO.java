package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.ProductEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanProductDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    // 이 코드는 LoanProductDAO 클래스의 멤버 변수로 데이터베이스 연결과 관련된 객체들을 선언하고, 초기값으로 null을 설정합니다. 

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection
                ("jdbc:oracle:thin:@//localhost:1521/xe", "DB1", "1234");
        return con;
    }

    //public static Connection getConnection() throws Exception: getConnection이라는 이름의 public, static 메서드를 선언합니다.
// 이 메서드는 Connection 객체를 반환하며, 예외(Exception)를 처리할 수 있습니다.
//
//Class.forName("oracle.jdbc.OracleDriver");: JDBC 드라이버 클래스를 동적으로 로드합니다. 이 예시에서는 Oracle JDBC 드라이버를 사용하고 있습니다.
// 해당 드라이버를 로드해야만 데이터베이스에 연결할 수 있습니다.
//
//Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "db1", "1234");: DriverManager 클래스를 사용하여 데이터베이스에 연결하는 Connection 객체를 생성합니다.
// 이 예시에서는 Oracle 데이터베이스에 "jdbc:oracle:thin:@//localhost:1521/xe" URL로 연결하고, "db1" 사용자명과 "1234" 비밀번호로 인증합니다.
// 이 정보는 해당 데이터베이스에 맞게 수정되어야 합니다.
//
//return con;: 생성된 Connection 객체를 반환합니다. 이를 통해 다른 코드에서 해당 연결을 활용할 수 있습니다.
//
//이 코드는 getConnection 메서드를 사용하여 Oracle 데이터베이스에 연결하는 Connection 객체를 반환하는 기능을 제공합니다.
// 이를 통해 다른 클래스나 메서드에서 데이터베이스와의 연결을 간편하게 활용할 수 있습니다.
    public ArrayList<ProductEntity> getLoanProductList() {
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();

        try {
            conn = getConnection();

            String sql = "select p_name, p_limit, p_interestrate from product where p_category in (?, ?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, "신용대출");
            ps.setString(2, "담보대출");
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setP_name(rs.getString(1));
                productEntity.setP_limit(rs.getInt(2));
                productEntity.setP_interestrate(rs.getDouble(3));
                productEntityList.add(productEntity);
            }

            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productEntityList;
    }
}
