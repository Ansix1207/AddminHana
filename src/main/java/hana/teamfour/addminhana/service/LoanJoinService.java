package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.CustomerDAO;
import hana.teamfour.addminhana.DTO.CustomerSignDTO;
import hana.teamfour.addminhana.DTO.ProductJoinSummaryDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoanJoinService {

    private CustomerDAO customerDAO;

    private final static Pattern rrn_pattern = Pattern.compile("^(\\d{6}\\D?\\d{1})(\\d{6})$");

    public LoanJoinService() {
        customerDAO = new CustomerDAO();
    }

//    public CustomerSummaryDTO getCustomerSummaryDTOById(Integer _c_id) {
//        CustomerEntity customerEntity = customerDAO.findById(_c_id);
//        if (customerEntity == null) {
//            return null;
//        }
//        return setCustomerSummaryDTO(customerEntity);
//    }
//
//    public CustomerSummaryDTO getCustomerSummaryDTOByRrn(String _c_rrn) {
//        CustomerEntity customerEntity = customerDAO.findByRrn(_c_rrn);
//        if (customerEntity == null) {
//            return null;
//        }
//        return setCustomerSummaryDTO(customerEntity);
//    }

//    public boolean updateCustomerDescription(CustomerSummaryDTO customerSummaryDTO) {
//        return customerDAO.updateCustomerSummary(customerSummaryDTO);
//    }

    private ProductJoinSummaryDTO productJoinSummaryDTO(CustomerEntity customerEntity) {
        String c_name = customerEntity.getC_name();
        String c_job = customerEntity.getC_job();
        String c_mobile = customerEntity.getC_mobile();
        String c_description = customerEntity.getC_description();
        return new ProductJoinSummaryDTO(c_name, c_job, c_mobile, c_description);
    }

    private CustomerSignDTO setCustomerSignDTO(CustomerEntity customerEntity) {
        return CustomerSignDTO.from(customerEntity);
    }

    private int getAgeFromRRN(String rrn) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int birthYear = Integer.parseInt(rrn.substring(0, 2));
        char ch = rrn.charAt(7);
        int preAge = year - (1900 + birthYear);
        int postAge = year - (2000 + birthYear);

        return (ch < '3') ? preAge : postAge;
    }

    private static String maskRRN(String rrn) {
        Matcher matcher = rrn_pattern.matcher(rrn);
        if (matcher.find()) {
            return new StringBuffer(matcher.group(1)).append("******").toString();
        }
        return rrn;
    }

    public static char getGenderFromRRN(String rrn) {
        //001026-1
        System.out.println("getGenderFromRRN : " + rrn);
        String[] stk = rrn.split("-");
        int partRRN = Integer.parseInt(stk[1].substring(0, 1));
        if (partRRN == 1 || partRRN == 3) {
            return 'M';
        } else if (partRRN == 2 || partRRN == 4) {
            return 'F';
        }
        return 'M';
    }

    public boolean signCustomer(CustomerSignDTO customerSignDTO) {
        try {
            CustomerSignDTO responseDTO = setCustomerSignDTO(customerDAO.insertCustomer(CustomerSignDTO.toEntity(customerSignDTO)));
            if (responseDTO.getC_name()
                    .equals(customerDAO.findByRRN(customerSignDTO.getC_rrn()).getC_name()))
                return true;
            else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.toString() + " | CustomerService - signCustomer : 회원 가입 실패!");
        }
        return false;
    }

    public boolean checkDuplicateByRRN(String rrn) {
        System.out.println("Service checkDuplicateByRRN : " + rrn);
        return customerDAO.checkDuplicateByRRN(rrn);
    }
}
