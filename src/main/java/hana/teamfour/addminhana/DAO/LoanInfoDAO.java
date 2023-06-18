package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.entity.AccountInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoanInfoDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "jyp", "1234");
        return con;
    }

    public String showLoanAccount(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<AccountInfo> list = new ArrayList<AccountInfo>();

        try {
            conn = getConnection();

            String sql = "SELECT ACC_PNAME, ACC_MATURITYDATE, ACC_INTERESTRATE, ACC_BALANCE ";
            sql += "FROM ACCOUNT ";
            sql += "WHERE ACC_CID = 37 AND ACC_P_CATEGORY IN ('신용대출', '담보대출') AND ACC_ISACTIVE = 'Y'";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            System.out.println("DAO 호출 성공");

            while (rs.next()) {
                AccountInfo accInfo = new AccountInfo();
                accInfo.setAcc_type("대출");
                accInfo.setAcc_pname(rs.getString(1));
                accInfo.setAcc_maturitydate(rs.getDate(2));
                accInfo.setAcc_interestrate(rs.getDouble(3));
                accInfo.setAcc_balance(rs.getInt(4));

                list.add(accInfo);
            }

            request.setAttribute("accInfo", list);
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "views/sessionOnAccInfo.jsp";
    }
}