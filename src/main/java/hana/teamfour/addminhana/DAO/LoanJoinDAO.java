package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanJoinDAO {
    private DataSource dataFactory;

    public LoanJoinDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean insertAccount(AccountEntity accountEntity) {
        boolean result = false;
//        AccountEntity 객체의 정보를 사용하여 데이터베이스에 새로운 계정을 삽입하는 작업을 수행합니다. 
        String sql = "INSERT INTO ACCOUNT VALUES(account_seq.nextval,?," +
                "?,?,?," +
                "select p_id from product where p_name = '급여하나 월복리 적금'," +
                "select p_category from product where p_name = '급여하나 월복리 적금'," +
                "select p_name from product where p_name = '급여하나 월복리 적금'," +
                "select p_int from product where p_name = '급여하나 월복리 적금'," +
                "?," +
                "1," +
                "select p_contract_month from product where p_name = '급여하나 월복리 적금'," +
                "?,'Y')";
//        String sql = "INSERT INTO ACCOUNT VALUES(account_seq.nextval,?)";
        System.out.println("sql = " + sql);
        try (Connection connection = getDataFactoryConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                System.out.println("set 하기 전까지는 왔다" + statement);
                statement.setInt(1, accountEntity.getAcc_id());
//                PreparedStatement의 첫 번째 파라미터 위치에 accountEntity의 acc_id 값을 설정한다는 의미입니다.
                statement.setInt(2, accountEntity.getAcc_cid());
                statement.setTimestamp(3, accountEntity.getAcc_date());
                statement.setInt(4, accountEntity.getAcc_balance());
                statement.setString(5, accountEntity.getAcc_password());
                statement.setInt(6, accountEntity.getAcc_pid());
                statement.setString(7, accountEntity.getAcc_p_category());
                statement.setString(8, accountEntity.getAcc_pname());
                statement.setDouble(9, accountEntity.getAcc_interestrate());
                statement.setInt(10, accountEntity.getAcc_collateralvalue());
                statement.setInt(11, accountEntity.getAcc_interest_day());
                statement.setInt(12, accountEntity.getAcc_contract_month());
                statement.setTimestamp(13, accountEntity.getAcc_maturitydate());
                statement.setString(14, String.valueOf(accountEntity.getAcc_isactive()));
                System.out.println("set은 잘 들어감  " + statement);
                statement.executeUpdate(); // 데이터를 삽입?
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        System.out.println("pass" + new AccountEntity());
        return result;
    }

//    public ArrayList<AccountEntity> insertJoin() {
//        ArrayList<AccountEntity> accountList = new ArrayList<>();
//        try (Connection conn = dataFactory.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement("SELECT ACC_ID FROM account")) {
//            try (ResultSet rs = pstmt.executeQuery()) {
//                while (rs.next()) {
//                    AccountEntity accountEntity = new AccountEntity();
//                    accountEntity.setAcc_id(rs.getInt(1));
//                    accountList.add(accountEntity);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("accountList = " + accountList);
//        return accountList;
//    }


    private Connection getDataFactoryConnection() throws SQLException {
        if (dataFactory == null) {
            throw new SQLException("DataFactory is null");
        }
        return dataFactory.getConnection();
    }
}
