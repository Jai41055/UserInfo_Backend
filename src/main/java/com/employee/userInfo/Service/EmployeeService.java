package com.employee.userInfo.Service;

import com.employee.userInfo.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
public interface EmployeeService {

    List<Employee> getEmployeeList() throws Exception;
    Employee addEmployee(Employee employee) throws Exception;

    Employee updateEmployee(Employee employee) throws Exception;

    String deleteEmployee(int id) throws Exception;

    Employee getEmployeeDetails(int id) throws Exception;
}
