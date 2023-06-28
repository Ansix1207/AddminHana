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

    public AccountEntity insertAccount(AccountEntity accountEntity) {
        String query = "INSERT INTO ACCOUNT VALUES(account_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println("query = " + query);
        try (Connection connection = dataFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, accountEntity.getAcc_id());
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new AccountEntity();
    }
}
