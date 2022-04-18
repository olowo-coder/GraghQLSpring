package com.example.graphqlspringv1;

import com.example.graphqlspringv1.model.Employee;
import com.example.graphqlspringv1.repository.EmployeeRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchEmployeeById implements DataFetcher<Employee> {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public FetchEmployeeById(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Employee get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        return employeeRepository.findById(dataFetchingEnvironment.getArgument("id")).get();
    }
}
