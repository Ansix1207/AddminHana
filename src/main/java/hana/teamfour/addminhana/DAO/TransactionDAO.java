package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.DTO.PaginationDTO;
import hana.teamfour.addminhana.Exception.BalanceInsufficientException;
import hana.teamfour.addminhana.Exception.DepositException;
import hana.teamfour.addminhana.Exception.TransferException;
import hana.teamfour.addminhana.entity.TransactionEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private DataSource dataFactory;

    public TransactionDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //출금 기능(update 기능으로 account balance 조정 -> 성공시 transaction에 insert하는데 성공여부 T 남김,
    //                                         -> 실패시 insert하는데 성공여부에 F
    public void insertWithdraw(TransactionEntity transactionEntity) throws BalanceInsufficientException {
        //pram1 = 출금할 계좌, param2 = 입금액, param3 = 결과 반환
        String procedureSql = "{CALL withdraw(?,?,?,?,?)}";
        int t_id = -99;
        //UPDATE ACCOUNT SET ACC_BALANCE = 100 WHERE ACC_ID = 1;
        int success = 0;
        try (Connection connection = dataFactory.getConnection()) {
            connection.setAutoCommit(false); //트랜잭션 처리를 위한 AutoCommit off, 트랜잭션 시작
            try (CallableStatement callableStatement = connection.prepareCall(procedureSql)) {
                //Start ACCOUNT UPDATE First.
                callableStatement.setInt(1, transactionEntity.getT_accid());
                callableStatement.setInt(2, transactionEntity.getT_amount());
                callableStatement.setString(3, transactionEntity.getT_description());
                callableStatement.registerOutParameter(4, Types.NUMERIC);
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.execute();
                success = callableStatement.getInt(4);
                t_id = callableStatement.getInt(5);
//                실행후 getInt로 결과 반환 (0이면 실패, 1이면 성공)
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
                if (success == 1) {
                    throw new BalanceInsufficientException("- 출금 성공!", t_id, success);
                } else if (success == -1) {
                    throw new BalanceInsufficientException("- 출금 실패 사유(해당하는 계좌 없음)", t_id, success);
                } else if (success == -2) {
                    connection.rollback();
                    throw new BalanceInsufficientException(" - 출금 실패 사유(잔액 부족)", t_id, success);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //입금 기능(update 기능으로 account balance 조정 -> 성공시 transaction에 insert하는데 성공여부 T 남김,
    //                                         -> 실패시 insert하는데 성공여부에 F
    public void insertDeposit(TransactionEntity transactionEntity) throws DepositException {
        //pram1 = 입금할 계좌, param2 = 입금액, param3 = 결과 반환
        String procedureSql = "{CALL deposit(?, ?, ?,?,?)}";
        String transactionSql = "INSERT INTO TRANSACTION VALUES(transaction_seq.nextval,?,?,?,?,?,?,?,?)";
        int success = 0;
        int t_id = -99;
        try (Connection connection = dataFactory.getConnection()) {
            connection.setAutoCommit(false); //트랜잭션 처리를 위한 AutoCommit off, 트랜잭션 시작
            try (CallableStatement callableStatement = connection.prepareCall(procedureSql)) {
                //Start ACCOUNT UPDATE First.
                callableStatement.setInt(1, transactionEntity.getT_accid());
                callableStatement.setInt(2, transactionEntity.getT_amount());
                callableStatement.setString(3, transactionEntity.getT_description());
                callableStatement.registerOutParameter(4, Types.NUMERIC);
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.execute();
                success = callableStatement.getInt(4);
                t_id = callableStatement.getInt(5);
                //실행후 getInt로 결과 반환 (0이면 실패, 1이면 성공)
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw e;
            } finally {
                connection.setAutoCommit(true);
                if (success == 1) {
                    throw new DepositException("- 입금 성공!", t_id, success);
                } else if (success == 2) {
                    connection.rollback();
                    throw new DepositException("- 입금 실패 사유(해당하는 계좌 없음)", t_id, success);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doTransfer(TransactionEntity transactionEntity) throws TransferException {
        //pram1 = 출금할 계좌, param2 = 입금액, param3 = 결과 반환
        String procedureSql = "{CALL transfer(?,?,?,?,?,?)}";
        String transactionSql = "INSERT INTO TRANSACTION VALUES(transaction_seq.nextval,?,?,?,?,?,?,?,?)";
        int t_id = -99;
        int success = 0;
        try (Connection connection = dataFactory.getConnection()) {
            connection.setAutoCommit(false); //트랜잭션 처리를 위한 AutoCommit off, 트랜잭션 시작
            try (CallableStatement callableStatement = connection.prepareCall(procedureSql)) {
                //Start ACCOUNT UPDATE First.
                callableStatement.setInt(1, transactionEntity.getT_accid());
                callableStatement.setInt(2, transactionEntity.getT_counterpart_id());
                callableStatement.setInt(3, transactionEntity.getT_amount());
                callableStatement.setString(4, transactionEntity.getT_description());
                callableStatement.registerOutParameter(5, Types.NUMERIC);
                callableStatement.registerOutParameter(6, Types.NUMERIC);
                callableStatement.execute();
                success = callableStatement.getInt(5);
                t_id = callableStatement.getInt(6);
                //실행후 getInt로 결과 반환 (0이면 실패, 1이면 성공)
                //  1 성공, -1 출금 계좌 없음, -2 출금 잔고 부족 , -3 입금 계좌 없음
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw e;
            } finally {
                connection.setAutoCommit(true);
                if (success == 1) {
                    throw new TransferException("- 계좌 이체 성공", t_id, success);
                } else if (success == -1) {
                    throw new TransferException("- 계좌이체 실패 (사유:출금 계좌 없음)", t_id, success);
                } else if (success == -2) {
                    throw new TransferException("- 계좌 이체 실패 (사유:잔액 부족)", t_id, success);
                } else if (success == -3) {
                    throw new TransferException("- 계좌 이체 실패 (사유:입금 계좌 없음)", t_id, success);
                } else {
                    throw new TransferException("- 계좌 이체 실패 (사유:오류 발생)", t_id, success);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionEntity findById(Integer t_id) {
        TransactionEntity transactionEntity = null;
        String query = "select * from transaction where t_id = ? ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setInt(1, t_id);
            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve data from the result set
                    transactionEntity = TransactionEntity.builder()
                            .t_id(resultSet.getInt("t_id"))
                            .t_accid(resultSet.getInt("t_accid"))
                            .t_counterpart_id(resultSet.getInt("t_counterpart_id"))
                            .t_date(resultSet.getTimestamp("t_date"))
                            .t_amount(resultSet.getInt("t_amount"))
                            .t_balance(resultSet.getInt("t_balance"))
                            .t_description(resultSet.getString("t_description"))
                            .t_status(resultSet.getString("t_status").charAt(0))
                            .t_type(resultSet.getString("t_type").charAt(0))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionEntity;
    }

    public TransactionEntity checkAccountByPassword(TransactionEntity transactionEntity, String password) {
        //계좌 잔고 반환을 위해서
        TransactionEntity result = null;
        String query = "select acc_id,acc_balance from account where acc_id = ? and acc_password =? and acc_isactive ='Y'";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setInt(1, transactionEntity.getT_accid());
            statement.setString(2, password);
            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve data from the result set
                    transactionEntity.setT_balance(resultSet.getInt("acc_balance"));
                    result = transactionEntity;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public List<TransactionEntity> findAll() {
        List<TransactionEntity> list = new ArrayList<>();
        String query = "select * from customer ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Integer t_id = rs.getInt("t_id");
                    Integer t_accid = rs.getInt("t_accid");
                    Integer t_counterpart_id = rs.getInt("t_counterpart_id");
                    Character t_type = rs.getString("t_type").charAt(0);
                    Timestamp t_date = rs.getTimestamp("t_date");
                    Integer t_amount = rs.getInt("t_amount");
                    String t_description = rs.getString("t_description");
                    Character t_status = rs.getString("t_status").charAt(0);
                    Integer t_balance = rs.getInt("t_balance");
                    TransactionEntity transactionEntity = new TransactionEntity(t_id, t_accid, t_counterpart_id, t_type, t_date, t_amount, t_description, t_status, t_balance);
                    list.add(transactionEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TransactionEntity> findAllByAccId(Integer _t_accid) {
        List<TransactionEntity> list = new ArrayList<>();
        String query = "select * from customer where t_accid = ? and t_status = 'T'";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, _t_accid);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Integer t_id = rs.getInt("t_id");
                    Integer t_accid = rs.getInt("t_accid");
                    Integer t_counterpart_id = rs.getInt("t_counterpart_id");
                    Character t_type = rs.getString("t_type").charAt(0);
                    Timestamp t_date = rs.getTimestamp("t_date");
                    Integer t_amount = rs.getInt("t_amount");
                    String t_description = rs.getString("t_description");
                    Character t_status = rs.getString("t_status").charAt(0);
                    Integer t_balance = rs.getInt("t_balance");
                    TransactionEntity transactionEntity = new TransactionEntity(t_id, t_accid, t_counterpart_id, t_type, t_date, t_amount, t_description, t_status, t_balance);
                    list.add(transactionEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Integer countRowsByAccId(Integer t_accid) {
        Integer count = null;
        String query = "select count(t_id) from transaction " +
                " where t_accid = ? " +
                "   and t_status = 'T'";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, t_accid);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Integer countFromDB = rs.getInt(1);
                    count = countFromDB;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<TransactionEntity> findWithPaginationByAccid(Integer _t_accid, PaginationDTO paginationDTO) {
        List<TransactionEntity> list = new ArrayList<>();
        String query = "" +
                "select * from (" +
                "   select rownum as rownumber, " +
                "           ordered_transaction.* from (" +
                "               select * from transaction " +
                "               where t_accid = ? " +
                "                 and t_status = 'T' " +
                "                 and t_description like ? " +
                "               order by t_accid desc ) ordered_transaction ) " +
                " where rownumber >= ? and rownumber < ?";
        Integer size = paginationDTO.getSize();
        Integer page = paginationDTO.getPage();
        String search = paginationDTO.getSearch();
        String orderBy = paginationDTO.getOrderBy();
        String ordering = paginationDTO.getOrdering();
        Integer startNum = 1 + (size * (page - 1));
        Integer lastNum = startNum + size;
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, _t_accid);
            statement.setString(2, search);
            statement.setInt(3, startNum);
            statement.setInt(4, lastNum);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Integer t_id = rs.getInt("t_id");
                    Integer t_accid = rs.getInt("t_accid");
                    Integer t_counterpart_id = rs.getInt("t_counterpart_id");
                    Character t_type = rs.getString("t_type").charAt(0);
                    Timestamp t_date = rs.getTimestamp("t_date");
                    Integer t_amount = rs.getInt("t_amount");
                    String t_description = rs.getString("t_description");
                    Character t_status = rs.getString("t_status").charAt(0);
                    Integer t_balance = rs.getInt("t_balance");
                    TransactionEntity transactionEntity = new TransactionEntity(t_id, t_accid, t_counterpart_id, t_type, t_date, t_amount, t_description, t_status, t_balance);
                    list.add(transactionEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
