package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.DTO.AssetSumDTO;
import hana.teamfour.addminhana.entity.AccountEntity;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private DataSource dataFactory;

    public AccountDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AccountEntity> getAccListById(Integer id, String category) {
        ArrayList<AccountEntity> list = new ArrayList<AccountEntity>();
        String query = "SELECT * " +
                "FROM ACCOUNT " +
                "WHERE ACC_CID = ? AND SUBSTR(ACC_P_CATEGORY, 3, 2) = ? AND ACC_ISACTIVE = 'Y' and sysdate < acc_maturitydate";

        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.setString(2, category);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer acc_id = resultSet.getInt("acc_id");
                    Integer acc_cid = resultSet.getInt("acc_cid");
                    Timestamp acc_date = resultSet.getTimestamp("acc_date");
                    Integer acc_balance = resultSet.getInt("acc_balance");
                    String acc_password = resultSet.getString("acc_password");
                    Integer acc_pid = resultSet.getInt("acc_pid");
                    String acc_p_category = resultSet.getString("acc_p_category");
                    String acc_pname = resultSet.getString("acc_pname");
                    Double acc_interestrate = resultSet.getDouble("acc_interestrate");
                    Integer acc_collateralvalue = resultSet.getInt("acc_collateralvalue");
                    Integer acc_interest_day = resultSet.getInt("acc_interest_day");
                    Integer acc_contract_month = resultSet.getInt("acc_contract_month");
                    Timestamp acc_maturitydate = resultSet.getTimestamp("acc_maturitydate");
                    Character acc_isactive = resultSet.getString("acc_isactive").charAt(0);
                    AccountEntity accountEntity = new AccountEntity(acc_id, acc_cid, acc_date, acc_balance, acc_password, acc_pid, acc_p_category, acc_pname, acc_interestrate, acc_collateralvalue, acc_interest_day, acc_contract_month, acc_maturitydate, acc_isactive);
                    list.add(accountEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AssetSumDTO> getSumOfAccBalance(Integer acc_cid) {
        List<AssetSumDTO> list = new ArrayList<>();
        String query = "select sum(acc_balance) as balance_sum, acc_p_category " +
                " from account " +
                " where acc_cid = ? " +
                "       and acc_isactive = 'Y' and sysdate < acc_maturitydate " +
                " group by acc_p_category";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, acc_cid);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer balanceSum = resultSet.getInt("balance_sum");
                    String accProductCategory = resultSet.getString("acc_p_category");
                    AssetSumDTO assetSumDTO = new AssetSumDTO(balanceSum, accProductCategory);
                    list.add(assetSumDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public AccountEntity getAccountNameAndCategoryByAccId(Integer acc_id, String type) {
        AccountEntity responseEntity = new AccountEntity();
        String query = "";
        if (type.equals("출금")) {
            query = "select acc_pname, acc_p_category " +
                    "from account " +
                    "where acc_id = ? " +
                    "and acc_isactive = 'Y'" +
                    "and acc_p_category IN ('보통예금')";
        }
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, acc_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String acc_pname = resultSet.getString("acc_pname");
                    String accProductCategory = resultSet.getString("acc_p_category");
                    responseEntity.setAcc_id(acc_id);
                    responseEntity.setAcc_p_category(accProductCategory);
                    responseEntity.setAcc_pname(acc_pname);
                } else {
                    responseEntity.setAcc_id(-999);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return responseEntity;
        }
    }

    public boolean insertAccount(AccountEntity accountEntity) throws SQLException {
        boolean result = true;
        String query = "INSERT INTO ACCOUNT VALUES(account_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = dataFactory.getConnection()) {
            connection.setAutoCommit(false); //트랜잭션 처리를 위한 AutoCommit off, 트랜잭션 시작
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, accountEntity.getAcc_cid());
                statement.setTimestamp(2, accountEntity.getAcc_date());
                statement.setInt(3, accountEntity.getAcc_balance());
                statement.setString(4, accountEntity.getAcc_password());
                statement.setInt(5, accountEntity.getAcc_pid());
                statement.setString(6, accountEntity.getAcc_p_category());
                statement.setString(7, accountEntity.getAcc_pname());
                statement.setDouble(8, accountEntity.getAcc_interestrate());
                statement.setInt(9, accountEntity.getAcc_collateralvalue());
                statement.setInt(10, accountEntity.getAcc_interest_day());
                statement.setInt(11, accountEntity.getAcc_contract_month());
                statement.setTimestamp(12, accountEntity.getAcc_maturitydate());
                statement.setString(13, String.valueOf(accountEntity.getAcc_isactive()));

                if (statement.executeUpdate() == 1) {
                    connection.commit();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                e.printStackTrace();
                result = false;
                throw e;
            }
        }
        System.out.println("DAO 결과: " + result);
        return result;
    }
}
