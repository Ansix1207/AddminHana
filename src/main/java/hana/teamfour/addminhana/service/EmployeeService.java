package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.EmployeeDAO;
import hana.teamfour.addminhana.DTO.EmployeeDTO;
import hana.teamfour.addminhana.entity.EmployeeEntity;

public class EmployeeService {

    public EmployeeDTO login(String id, String pw) {
        EmployeeDAO employeeDAO = EmployeeDAO.getInstance(); //싱글톤 패턴
        EmployeeEntity employeeEntity = employeeDAO.login(id, pw);
        EmployeeDTO employeeDTO = null;
        if (employeeEntity != null) {
            employeeDTO = new EmployeeDTO(employeeEntity);
        }
        return employeeDTO;
    }
}
