package com.sadang.lotto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadang.lotto.exception.ResourceNotFoundException;
import com.sadang.lotto.model.Employee;
import com.sadang.lotto.repository.EmployeeRepository;

/**
 * 
 * @author yang/2020.01.29 - rest api 기초 crud 작성
 *  2020.01.29 - REST API TEST SITE : https://resttesttest.com/
 *   
 */
@RestController
@RequestMapping("/rest")
public class EmployeeController {
	@Autowired(required=true)
    private EmployeeRepository employeeRepository;
	private Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	// HTTP Method: GET 
 	// Request URL: http://localhost:8081/rest/employees
	@GetMapping("/employees")
    public List<Employee> getAllEmployees() 
    		throws ResourceNotFoundException {
		List<Employee> response = employeeRepository.findAll();
		logger.info("response : " + response.toString());
		return response;
    }

	// HTTP Method: GET 
	// Request URL: http://localhost:8080/rest/employees/11
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
    		throws ResourceNotFoundException {
    	Employee employee = employeeRepository.findById(employeeId)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    	logger.info("response Body is : " + employee);
    	return ResponseEntity.ok().body(employee);
    }
    
    // HTTP Method: POST 
    // Request URL: http://localhost:8080/rest/employees 
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee)
    		throws ResourceNotFoundException{
        return employeeRepository.save(employee);
    }
    
    //HTTP Method: GET 
    //Request URL: http://localhost:8080/rest/emploees/7
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @Valid @RequestBody Employee employeeDetails) 
        	throws ResourceNotFoundException {
    	Employee employee = employeeRepository.findById(employeeId)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

    	employee.setEmailId(employeeDetails.getEmailId());
    	employee.setLastName(employeeDetails.getLastName());
    	employee.setFirstName(employeeDetails.getFirstName());
    	final Employee updatedEmployee = employeeRepository.save(employee);
    	return ResponseEntity.ok(updatedEmployee);
    }

    // HTTP Method: DELETE
    // Request URL: http://localhost:8080/rest/employees/11
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId, Model model)
    		throws ResourceNotFoundException {
    	Employee employee = employeeRepository.findById(employeeId)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

    	employeeRepository.delete(employee);
    	Map<String, Boolean> response = new HashMap<>();
    	response.put("deleted", Boolean.TRUE);
    	return response;
    }
}
