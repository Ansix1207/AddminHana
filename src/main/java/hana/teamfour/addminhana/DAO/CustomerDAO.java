package hana.teamfour.addminhana.DAO;

import hana.teamfour.addminhana.DTO.CustomerSignDTO;
import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private DataSource dataFactory;
    private Connection conn;
    private PreparedStatement pstmt;
    Throwable occuredException = null;
    public CustomerDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerEntity findById(Integer _c_id) {
        CustomerEntity customerEntity = null;
        try {
            conn = dataFactory.getConnection();
            String query = "select * from customer where c_id = ? ";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, _c_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Integer c_id = rs.getInt("c_id");
                String c_name = rs.getString("c_name");
                String c_rrn = rs.getString("c_rrn");
                Character c_gender = rs.getString("c_gender").charAt(0);
                String c_address = rs.getString("c_address");
                String c_mobile = rs.getString("c_mobile");
                String c_job = rs.getString("c_job");
                String c_description = rs.getString("c_description");
                Integer e_id = rs.getInt("e_id");
                customerEntity = new CustomerEntity(c_id, c_name, c_rrn, c_gender, c_address, c_mobile, c_job, c_description, e_id);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerEntity;
    }

    public boolean checkDuplicateByRRN(String rrn){
        try {
            Connection conn = dataFactory.getConnection();
            String query = "SELECT * FROM customer WHERE C_RRN = ?";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, rrn);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("checkDuplicateByRRN");
            if (rs.next()){
                System.out.println("checkDuplicateByRRN : 성공!(중복 됨)");
                rs.close();
                return true;
            }
            rs.close();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                closeConn(conn);
                closePstmt(pstmt);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public CustomerEntity findByRRN(String _rrn){
        CustomerEntity customerEntity = null;
        try {
            Connection conn = dataFactory.getConnection();
            String query = "SELECT * FROM customer WHERE C_RRN = ?";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, _rrn);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("findByRRN");
            if (rs.next()) {
                Integer c_id = rs.getInt("c_id");
                String c_name = rs.getString("c_name");
                String c_rrn = rs.getString("c_rrn");
                Character c_gender = rs.getString("c_gender").charAt(0);
                String c_job = rs.getString("c_job");
                String c_address = rs.getString("c_address");
                String c_mobile = rs.getString("c_mobile");
                String c_description = rs.getString("c_description");
                Integer e_id = rs.getInt("e_id");
                customerEntity = new CustomerEntity(c_id, c_name, c_rrn, c_gender, c_address, c_mobile, c_job, c_description, e_id);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                closeConn(conn);
                closePstmt(pstmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customerEntity;
    }

//    View <-> Controller <-> service <-> dao <-> db
//    dto             dto        dto     entity
    public CustomerEntity insertCustomer(CustomerEntity customerEntity){
        try {
            Connection conn = dataFactory.getConnection();
            conn.setAutoCommit(false); //트랜잭션 처리를 위한 AutoCommit off, 트랜잭션 시작
            String query = "INSERT INTO CUSTOMER VALUES(-6,?,?,?,?,?,?,?,?)";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, customerEntity.getC_name());//String name
            pstmt.setString(2, customerEntity.getC_rrn());//String 주민번호 rrn
            pstmt.setString(3, String.valueOf(customerEntity.getC_gender()));//String  성별 gender (M,F)
            pstmt.setString(4, customerEntity.getC_address());//String 주소 address
            pstmt.setString(5, customerEntity.getC_mobile());//String 주소 mobile
            pstmt.setString(6, customerEntity.getC_job());//String 직업(공무원,직장인,전문직,사업자,일반) job
            pstmt.setString(7, customerEntity.getC_description());//String 설명 description
            pstmt.setInt(8, customerEntity.getE_id());//int 주소 e_id
            System.out.println(pstmt.toString());
            if (pstmt.executeUpdate() == 1) {
                System.out.println("삽입 성공");
                conn.commit();
                return findByRrn(customerEntity.getC_rrn());
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.out.println("CustomerDAO rollback error : " + ex.getMessage());
                }
            }
            System.out.println("CustomerDAO in Throwable error : " + e.getMessage());
        } finally {
            try {
                closePstmt(pstmt);
                closeConn(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new CustomerEntity();
    }

    public boolean updateCustomerSummary(CustomerSummaryDTO customerSummaryDTO) {
        Integer c_id = customerSummaryDTO.getC_id();
        String c_name = customerSummaryDTO.getC_name();
        String c_rrn = customerSummaryDTO.getC_rrn();
        Character c_gender = customerSummaryDTO.getC_gender();
        String c_job = customerSummaryDTO.getC_job();
        String c_description = customerSummaryDTO.getC_description();
        try {
            conn = dataFactory.getConnection();
            String query = "update customer set c_name=?, c_rrn=?," +
                    " c_gender=?, c_job=?, c_description=? " +
                    " where c_id=? ";
            System.out.println("query = " + query);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, c_name);
            pstmt.setString(2, c_rrn);
            pstmt.setString(3, c_gender.toString());
            pstmt.setString(4, c_job);
            pstmt.setString(5, c_description);
            pstmt.setInt(6, c_id);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private void closeConn(Connection conn) throws SQLException {
        if (conn != null) {
            conn.setAutoCommit(true);
            conn.close();
        }
    }
    private void closePstmt(PreparedStatement pstmt) throws SQLException{
        if (pstmt != null) {
            pstmt.close();
        }
    }
}
