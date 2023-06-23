package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.CustomerDAO;
import hana.teamfour.addminhana.DTO.CustomerSignDTO;
import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {
    private CustomerDAO customerDAO;

    private final static Pattern rrn_pattern = Pattern.compile("^(\\d{6}\\D?\\d{1})(\\d{6})$");

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public CustomerSummaryDTO getCustomerSummaryDTOById(Integer _c_id) {
        return setCustomerSummaryDTO(customerDAO.findById(_c_id));
    }

    public boolean updateCustomerDescription(CustomerSummaryDTO customerSummaryDTO) {
        return customerDAO.updateCustomerSummary(customerSummaryDTO);
    }

    private CustomerSummaryDTO setCustomerSummaryDTO(CustomerEntity customerEntity) {
        Integer c_id = customerEntity.getC_id();
        String c_name = customerEntity.getC_name();
        String c_rrn = customerEntity.getC_rrn();
        c_rrn = maskRRN(c_rrn);
        Character c_gender = customerEntity.getC_gender();
        String c_job = customerEntity.getC_job();
        String c_description = customerEntity.getC_description();
        Integer c_age = getAgeFromRRN(c_rrn);

        return new CustomerSummaryDTO(c_id, c_name, c_rrn, c_gender, c_job, c_description, c_age);
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
        int partRRN = Integer.parseInt(rrn.substring(7,8));
        if(partRRN==1 || partRRN==3){
            return 'M';
        } else if (partRRN==1 || partRRN==3) {
            return 'F';
        }
        return 'M';
    }
    public String signCustomer(CustomerSignDTO customerSignDTO){
        try {
            if(customerDAO.insertCustomer(CustomerSignDTO.toEntity(customerSignDTO)).getC_name().equals(customerSignDTO.getC_name()))
                return"CustomerService - signCustomer : 회원 가입 성공!";
            else{
                return "CustomerService - signCustomer : 회원 가입 실패!";
            }
        } catch (Throwable e) {
            System.out.println(e.toString() + " | CustomerService - signCustomer : 회원 가입 실패!");
        }
        return null;
    }
}
