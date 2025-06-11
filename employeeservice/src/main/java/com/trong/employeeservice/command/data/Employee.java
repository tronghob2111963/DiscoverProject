package com.trong.employeeservice.command.data;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
