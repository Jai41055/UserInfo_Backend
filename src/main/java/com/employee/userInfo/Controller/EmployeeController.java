package com.employee.userInfo.Controller;

import com.employee.userInfo.Service.EmployeeService;
import com.employee.userInfo.Model.Employee;
import com.employee.userInfo.Utility.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final Log logger = LogFactory.getLog(EmployeeController.class);

    //Get all employee Details
    @GetMapping("allEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        logger.info("-----" + this.getClass().getName() + "-- " + "getAllEmployees " + Constants.BEGIN);
        List<Employee> employeeList = null;
        try {
            employeeList = employeeService.getEmployeeList();
        } catch (Exception e) {
            logger.info("-----" + this.getClass().getName() + " -- " + Constants.WRONG + " getAllEmployees " + Constants.METHOD);
            logger.error(e.getMessage());
        }
        logger.info("-----" + this.getClass().getName() + "-- " + "getAllEmployees " + Constants.END);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    //Creating the new Employee
    @PostMapping("addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        logger.info("-----" + this.getClass().getName() + "-- " + "addEmployee " + Constants.BEGIN);
        Employee emp = null;
        try {
            emp = employeeService.addEmployee(employee);
        } catch (Exception e) {
            logger.info("-----" + this.getClass().getName() + " -- " + Constants.WRONG + " addEmployee " + Constants.METHOD);
            logger.error(e.getMessage());
        }
        logger.info("-----" + this.getClass().getName() + "-- " + "addEmployee " + Constants.END);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    };

    //Get the Employee Details
    @GetMapping("getDetails/{id}")
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("id") int id){
        logger.info("-----" + this.getClass().getName() + "-- " + "getEmployeeDetails " + Constants.BEGIN);
        Employee emp = null;
        try {
            emp = employeeService.getEmployeeDetails(id);
        } catch (Exception e) {
            logger.info("-----" + this.getClass().getName() + " -- " + Constants.WRONG + " getEmployeeDetails " + Constants.METHOD);
            logger.error(e.getMessage());
        }
        logger.info("-----" + this.getClass().getName() + "-- " + "getEmployeeDetails " + Constants.END);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    };

    //Update the employee Details
    @PutMapping("updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        logger.info("-----" + this.getClass().getName() + "-- " + "updateEmployee " + Constants.BEGIN);
        Employee emp = null;
        try {
            emp = employeeService.updateEmployee(employee);
        } catch (Exception e) {
            logger.info("-----" + this.getClass().getName() + " -- " + Constants.WRONG + " updateEmployee " + Constants.METHOD);
            logger.error(e.getMessage());
        }
        logger.info("-----" + this.getClass().getName() + "-- " + "updateEmployee " + Constants.END);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    };

    //Delete the employee
    @DeleteMapping("deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        logger.info("-----" + this.getClass().getName() + "-- " + "deleteEmployee " + Constants.BEGIN);
        String message = "";
        try {
            message = employeeService.deleteEmployee(id);
        } catch (Exception e) {
            logger.info("-----" + this.getClass().getName() + " -- " + Constants.WRONG + " deleteEmployee " + Constants.METHOD);
            logger.error(e.getMessage());
        }
        logger.info("-----" + this.getClass().getName() + "-- " + "deleteEmployee " + Constants.END);
        return new ResponseEntity<>(message, HttpStatus.OK);
    };
}
