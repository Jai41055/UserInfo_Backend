package com.employee.userInfo.ServiceImpl;

import com.employee.userInfo.Model.Employee;
import com.employee.userInfo.Model.User;
import com.employee.userInfo.Repository.EmployeeDAO;
import com.employee.userInfo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUserName(username);
        Optional<Employee> emp = employeeDAO.findByEmployeeName(username);
        User user = userDetail.get();
        Employee employee = emp.get();
        user.setEmployeeNumber(employee.getEmployeeNumber());
        user.setEmployeeId(employee.getId());
        user.setEmployeeEmail(employee.getMobileNumber());
        user.setEmployeeMobile(employee.getMobileNumber());
        System.out.println(user);
        return user;
    }
}
