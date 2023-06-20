package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.CustomerDAO;
import hana.teamfour.addminhana.DTO.CustomerSummaryDTO;
import hana.teamfour.addminhana.entity.CustomerEntity;

public class CustomerService {
    CustomerDAO customerDAO = new CustomerDAO();
    CustomerSummaryDTO customerSummaryDTO;

    public CustomerSummaryDTO getCustomerSummaryDTOById(Integer _c_id) {
        System.out.println("Service 호출");
        return setCustomerSummaryDTO(customerDAO.findById(_c_id));
    }

    private CustomerSummaryDTO setCustomerSummaryDTO(CustomerEntity customerEntity) {
        Integer c_id = customerEntity.getC_id();
        String c_name = customerEntity.getC_name();
        String c_rrn = customerEntity.getC_rrn();
        Character c_gender = customerEntity.getC_gender();
        String c_job = customerEntity.getC_job();
        String c_description = customerEntity.getC_description();
        customerSummaryDTO = new CustomerSummaryDTO(c_id, c_name, c_rrn, c_gender, c_job, c_description);
        return customerSummaryDTO;
    }
}
