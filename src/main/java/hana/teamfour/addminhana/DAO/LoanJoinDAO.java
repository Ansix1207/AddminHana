package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

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
        String sql = "INSERT INTO ACCOUNT VALUES(?,?,?,?,?," +
                "?,?,?,?,?," +
                "?,?,?,?)";
        try (Connection connection = getDataFactoryConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                Random random = new Random();
                int randomValue1 = random.nextInt(5000);
                int randomValue2 = random.nextInt(5000);
                statement.setInt(1, randomValue1);
                statement.setInt(2, randomValue2);
                statement.setTimestamp(3, accountEntity.getAcc_date());
                statement.setInt(4, accountEntity.getAcc_balance());
                statement.setString(5, accountEntity.getAcc_password());
                statement.setInt(6, accountEntity.getAcc_pid());
                statement.setString(7, accountEntity.getAcc_p_category());
                statement.setString(8, accountEntity.getAcc_pname());
                statement.setDouble(9, accountEntity.getAcc_interestrate());
                statement.setInt(10, accountEntity.getAcc_collateralvalue());
                statement.setInt(11, 1);
                statement.setInt(12, accountEntity.getAcc_contract_month());
                statement.setTimestamp(13, accountEntity.getAcc_maturitydate());
                statement.setString(14, "Y");
                System.out.println("statement" + statement);
                statement.executeUpdate();
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        System.out.println("pass" + new AccountEntity());
        return result;
    }

    private Connection getDataFactoryConnection() throws SQLException {
        if (dataFactory == null) {
            throw new SQLException("DataFactory is null");
        }
        return dataFactory.getConnection();
    }
}
