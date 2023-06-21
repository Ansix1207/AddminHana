package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanAccountDAO {
    private DataSource dataFactory;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public LoanAccountDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AccountEntity> getLoanInfoList() {
        ArrayList<AccountEntity> list = new ArrayList<AccountEntity>();

        try {
            conn = dataFactory.getConnection();

            String sql = "SELECT ACC_P_CATEGORY, ACC_PNAME, ACC_MATURITYDATE, ACC_INTERESTRATE, ACC_BALANCE " +
                    "FROM ACCOUNT " +
                    "WHERE ACC_CID = 37 AND ACC_P_CATEGORY IN ('신용대출', '담보대출') AND ACC_ISACTIVE = 'Y'";

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
