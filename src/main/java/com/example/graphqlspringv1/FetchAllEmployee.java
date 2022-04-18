package com.example.graphqlspringv1;

import com.example.graphqlspringv1.model.Employee;
import com.example.graphqlspringv1.repository.EmployeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FetchAllEmployee implements DataFetcher<List<Employee>> {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public FetchAllEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return employeeRepository.findAll();
    }
}
