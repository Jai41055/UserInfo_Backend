package com.employee.userInfo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "example_sequence")
    @SequenceGenerator(name = "example_sequence", sequenceName = "example_sequence", initialValue = 1, allocationSize = 1)
    private int id;

    @Column(name= "emp_no", length = 10, unique = true, nullable = false)
    @Size(min = 5, max = 10)
    private String employeeNumber;

    @Column(name = "emp_name", length = 50, nullable = false)
    @Size(min = 5, max = 50)
    private String employeeName;

    @Column(name = "mobile", length = 10, unique = true)
    private String mobileNumber;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String emailId;

}
