package com.example.graphqlspringv1.controller;

import com.example.graphqlspringv1.GraphQLService;
import com.example.graphqlspringv1.model.Employee;
import com.example.graphqlspringv1.repository.EmployeeRepository;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final GraphQLService graphQLService;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(GraphQLService graphQLService, EmployeeRepository employeeRepository) {
        this.graphQLService = graphQLService;
        this.employeeRepository = employeeRepository;
    }


    @PostMapping
    public ResponseEntity<ExecutionResult> getAllEmployees(@RequestBody final String query){
        ExecutionResult result = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostConstruct
    private void saveEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("1", 23, "Ben", "IT"));
        employeeList.add(new Employee("2", 45, "John", "DevOps"));
        employeeList.add(new Employee("3", 32, "Sam", "Dev"));
        employeeList.add(new Employee("4", 28, "Mary", "CS"));
        employeeList.add(new Employee("5", 29, "James", "Sales"));
        employeeList.add(new Employee("6", 37, "Lan", "Engineer"));
        employeeRepository.saveAll(employeeList);
    }
}
