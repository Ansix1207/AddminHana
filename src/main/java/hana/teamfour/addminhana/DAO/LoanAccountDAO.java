package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanAccountDAO {
    private DataSource dataFactory;

    public LoanAccountDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AccountEntity> getLoanAccListById(Integer id) {
        ArrayList<AccountEntity> list = new ArrayList<AccountEntity>();
        String query = "SELECT ACC_P_CATEGORY, ACC_PNAME, ACC_MATURITYDATE, ACC_INTERESTRATE, ACC_BALANCE " +
                "FROM ACCOUNT " +
                "WHERE ACC_CID = ? AND ACC_P_CATEGORY IN ('신용대출', '담보대출') AND ACC_ISACTIVE = 'Y'";

        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AccountEntity accountEntity = new AccountEntity();
                    accountEntity.setAcc_p_category(resultSet.getString(1));
                    accountEntity.setAcc_pname(resultSet.getString(2));
                    accountEntity.setAcc_maturitydate(resultSet.getTimestamp(3));
                    accountEntity.setAcc_interestrate(resultSet.getDouble(4));
                    accountEntity.setAcc_balance(resultSet.getInt(5));

                    list.add(accountEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}