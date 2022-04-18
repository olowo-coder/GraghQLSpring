package com.example.graphqlspringv1.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private String id;

    private int age;
    private String name;
    private String department;
}
