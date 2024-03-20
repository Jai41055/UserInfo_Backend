package com.employee.userInfo.Repository;

import com.employee.userInfo.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
       Optional<Employee> findByEmployeeName(String userName);
}
