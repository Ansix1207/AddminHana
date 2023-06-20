package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanAccountDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "admin_hana", "1234");
        return con;
    }

    public ArrayList<AccountEntity> getLoanInfoList() {
        ArrayList<AccountEntity> list = new ArrayList<AccountEntity>();

        try {
            conn = getConnection();

            String sql = "SELECT ACC_P_CATEGORY, ACC_PNAME, ACC_MATURITYDATE, ACC_INTERESTRATE, ACC_BALANCE ";
            sql += "FROM ACCOUNT ";
            sql += "WHERE ACC_CID = 37 AND ACC_P_CATEGORY IN ('신용대출', '담보대출') AND ACC_ISACTIVE = 'Y'";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("LoanAccountDAO 호출 성공");

            while (rs.next()) {
                AccountEntity accountEntity = new AccountEntity();
                accountEntity.setAcc_p_category(rs.getString(1));
                accountEntity.setAcc_pname(rs.getString(2));
                accountEntity.setAcc_maturitydate(rs.getTimestamp(3));
                accountEntity.setAcc_interestrate(rs.getDouble(4));
                accountEntity.setAcc_balance(rs.getInt(5));

                list.add(accountEntity);
            }

            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
