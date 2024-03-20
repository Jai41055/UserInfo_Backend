package com.employee.userInfo.ServiceImpl;

import com.employee.userInfo.Repository.EmployeeDAO;
import com.employee.userInfo.Model.Employee;
import com.employee.userInfo.Service.EmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;

    private final Log logger = LogFactory.getLog(EmployeeServiceImpl.class);

    @Override
    public List<Employee> getEmployeeList() throws Exception {
        List<Employee> empList;
        try {
            empList = employeeDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return empList;
    }

    @Override
    public Employee addEmployee(Employee employee) throws Exception {
        Employee emp;
        try {
            int number = (int)(Math.random() * 100000);
            employee.setEmployeeNumber(String.valueOf(number));
            emp = employeeDAO.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return emp;
    }

    @Override
    public Employee updateEmployee(Employee employee) throws Exception {
        Employee emp;
        try {
            emp = employeeDAO.save(employee);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return emp;
    }

    @Override
    public String deleteEmployee(int id) throws Exception {
        String message = "";
        try {
            employeeDAO.deleteById(id);
            message = "Record deleted Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return message;
    }

    @Override
    public Employee getEmployeeDetails(int id) throws Exception {
        Employee emp = null;
        try {
            Optional<Employee> employ = employeeDAO.findById(id);
            if(employ.isPresent()) emp = employ.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return emp;
    }
}
