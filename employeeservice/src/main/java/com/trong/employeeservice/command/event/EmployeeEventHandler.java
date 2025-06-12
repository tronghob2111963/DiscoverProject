package com.trong.employeeservice.command.event;


import com.trong.employeeservice.command.data.Employee;
import com.trong.employeeservice.command.data.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
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
    public void on(EmployeeUpdatedEvent event) throws Exception {

        //lay employee theo id
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());

        //xu ly ngoai le xem employee co ton tai hay khong
        Employee employee = oldEmployee.orElseThrow(() -> new Exception("Employee not found"));

        //copy data tu event vao employee
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }

    @EventHandler
    @DisallowReplay
    public void on(EmployeeDeletedEvent event) throws Exception {
        try{
            //kiem tra xem employee co ton tai hay khong
            employeeRepository.findById(event.getId()).orElseThrow(() -> new Exception ("Employee not found"));
            //xoa employee theo id
            employeeRepository.deleteById(event.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
