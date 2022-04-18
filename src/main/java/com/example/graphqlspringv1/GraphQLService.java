package com.example.graphqlspringv1;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:graphql/employee.graphqls")
    private Resource resource;

    @Autowired
    private FetchAllEmployee fetchAllEmployee;

    @Autowired
    private FetchEmployeeById fetchEmployeeById;

    private GraphQL graphQL;

    private void getSchema() throws IOException {
        File schema = resource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schema);
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("", fetchAllEmployee)
                        .dataFetcher("", fetchEmployeeById)).build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }

}
