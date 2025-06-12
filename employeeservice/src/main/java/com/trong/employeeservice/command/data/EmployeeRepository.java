package com.trong.employeeservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {


    @Query("select e from Employee e where e.isDisciplined = :isDisciplined")
    List<Employee> findAllByIsDisciplined(Boolean isDisciplined);
}
