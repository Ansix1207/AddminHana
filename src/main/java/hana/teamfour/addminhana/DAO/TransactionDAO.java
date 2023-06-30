package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.Exception.BalanceInsufficientException;
import hana.teamfour.addminhana.entity.TransactionEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

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
                System.out.println("출금 커밋 직전이야~ = ");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                System.out.println("finally in 입금 p_id = " + t_id);
                connection.setAutoCommit(true);
                if (success == 1) {
                    System.out.println("TransactinDAO : 출금 성공");
                    throw new BalanceInsufficientException("- 출금 성공!", t_id);
                } else if (success == -1) {
                    System.out.println("TransactinDAO : 출금 실패 사유(해당하는 계좌 없음)");
                    throw new BalanceInsufficientException("- 출금 실패 사유(해당하는 계좌 없음)", t_id);
                } else if (success == -2) {
                    System.out.println("TransactinDAO : 출금 실패 사유(잔고가 부족함)");
                    connection.rollback();
                    throw new BalanceInsufficientException(" - 출금 실패 사유(잔고 부족)", t_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //입금 기능(update 기능으로 account balance 조정 -> 성공시 transaction에 insert하는데 성공여부 T 남김,
    //                                         -> 실패시 insert하는데 성공여부에 F
    public int insertDeposit(TransactionEntity transactionEntity) {
        //pram1 = 입금할 계좌, param2 = 입금액, param3 = 결과 반환
        String procedureSql = "{CALL deposit(?, ?, ?,?,?)}";
        String transactionSql = "INSERT INTO TRANSACTION VALUES(transaction_seq.nextval,?,?,?,?,?,?,?,?)";
        int success = 0;
        int p_id = -99;
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
                p_id = callableStatement.getInt(5);
                //실행후 getInt로 결과 반환 (0이면 실패, 1이면 성공)
                if (success == 1) {
                    System.out.println("TransactinDAO : 입금 성공");
                } else if (success == -1) {
                    System.out.println("TransactinDAO : 입금 실패 사유(해당하는 계좌 없음)");
                }
                System.out.println("insertDeposit DAO : p_id = " + p_id);
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw e;
            } finally {
                connection.setAutoCommit(true);
                return p_id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doTransfer(TransactionEntity transactionEntity) {
        //pram1 = 출금할 계좌, param2 = 입금액, param3 = 결과 반환
        String procedureSql = "{CALL transfer(?,?,?,?,?,?)}";
        String transactionSql = "INSERT INTO TRANSACTION VALUES(transaction_seq.nextval,?,?,?,?,?,?,?,?)";
        int p_id;
        System.out.println("DAO doTransfer 일단 들어옴");
        //UPDATE ACCOUNT SET ACC_BALANCE = 100 WHERE ACC_ID = 1;
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
                p_id = callableStatement.getInt(6);
                System.out.println("DAO : p_id = " + p_id);
                //실행후 getInt로 결과 반환 (0이면 실패, 1이면 성공)
                if (success == 1) {
                    System.out.println("TransactinDAO : 계좌이체 성공");
                } else if (success == 0) {
                    System.out.println("TransactinDAO : 출금 실패 오류 발생");
                } else if (success == -1) {
                    System.out.println("TransactinDAO : 출금 실패 사유(해당하는 계좌 없음)");
                } else if (success == -2) {
                    System.out.println("TransactinDAO : 출금 실패 사유(잔고가 부족함)");
                }
                System.out.println("커밋 직전이야!");
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("근데 롤백됨 ㅋ");
                e.printStackTrace();
                throw e;
            } finally {
                System.out.println("TransactionDAO 들어옴 : Transfer 넘음");
                connection.setAutoCommit(true);
                return success;
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
            System.out.println("query = " + query);
            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve data from the result set
                    System.out.println("결과 있음");
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
                    System.out.println(transactionEntity.toString());
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
        String query = "select acc_id,acc_balance from account where acc_id = ? and acc_password =? ";
        try (Connection connection = dataFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Set parameters
            statement.setInt(1, transactionEntity.getT_accid());
            statement.setString(2, password);
            System.out.println("query = " + query);
            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                if (resultSet.next()) {
                    // Retrieve data from the result set
                    System.out.println("비밀번호 조회 결과 있음 : " + resultSet.getInt("acc_id"));
                    System.out.println("계좌 잔액 조회 결과 있음 : " + resultSet.getInt("acc_balance"));
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
}
