package com.trong.employeeservice.command.event;


import com.trong.employeeservice.command.data.Employee;
import com.trong.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeEventHandler {
    @Autowired
    EmployeeRepository employeeRepository;


    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event,employee);
        employeeRepository.save(employee);
    }


    @EventHandler
    public void on(EmployeeUpdatedEvent event) {

        //lay employee theo id
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());

        //xu ly ngoai le xem employee co ton tai hay khong
        Employee employee = oldEmployee.orElseThrow(() -> new RuntimeException("Employee not found"));

        //copy data tu event vao employee
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }
}
