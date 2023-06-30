package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.CustomerDAO;
import hana.teamfour.addminhana.DTO.CustomerDTO;
import hana.teamfour.addminhana.DTO.CustomerSignDTO;
import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.DTO.PaginationDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {
    private CustomerDAO customerDAO;


    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public CustomerSummaryDTO getCustomerSummaryDTOById(Integer _c_id) {
        CustomerEntity customerEntity = customerDAO.findById(_c_id);
        if (customerEntity == null) {
            return null;
        }
        return setCustomerSummaryDTO(customerEntity);
    }

    public CustomerSummaryDTO getCustomerSummaryDTOByRRN(String _c_rrn) {
        CustomerEntity customerEntity = customerDAO.findByRRN(_c_rrn);
        if (customerEntity == null) {
            return null;
        }
        return setCustomerSummaryDTO(customerEntity);
    }

    public boolean updateCustomerDescription(CustomerSummaryDTO customerSummaryDTO) {
        CustomerEntity customerEntity = customerDAO.findById(customerSummaryDTO.getC_id());
        if (customerEntity == null) {
            return false;
        }
        customerEntity.setC_description(customerSummaryDTO.getC_description());
        return customerDAO.updateCustomerEntity(customerEntity);
    }

    private CustomerSummaryDTO setCustomerSummaryDTO(CustomerEntity customerEntity) {
        CustomerSummaryDTO customerSummaryDTO = CustomerSummaryDTO.from(customerEntity);
        String c_rrn = customerEntity.getC_rrn();
        customerSummaryDTO.setC_age(getAgeFromRRN(c_rrn));
        customerSummaryDTO.setC_rrn(maskRRN(c_rrn));
        return customerSummaryDTO;
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
        Pattern rrn_pattern = Pattern.compile("^(\\d{6}\\D?\\d{1})(\\d{6})$");
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

    public List<CustomerDTO> getCustomerList() {
        List<CustomerDTO> customerList = new ArrayList<>();
        List<CustomerEntity> customerEntityList = customerDAO.findAll();
        for (CustomerEntity customerEntity : customerEntityList) {
            CustomerDTO customerDTO = new CustomerDTO(customerEntity);
            customerList.add(customerDTO);
        }
        return customerList;
    }

    public List<CustomerDTO> getCustomerListWithPagination(PaginationDTO paginationDTO) {
        List<CustomerDTO> customerList = new ArrayList<>();
        System.out.println("paginationDTO = " + paginationDTO);
        List<CustomerEntity> customerEntityList = customerDAO.findWithPagination(paginationDTO);
        for (CustomerEntity customerEntity : customerEntityList) {
            CustomerDTO customerDTO = new CustomerDTO(customerEntity);
            customerList.add(customerDTO);
        }
        return customerList;
    }

    public Integer getCountRow() {
        return customerDAO.countRows();
    }
}
