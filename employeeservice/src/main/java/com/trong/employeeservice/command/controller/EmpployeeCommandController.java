package com.trong.employeeservice.command.controller;


import com.trong.employeeservice.command.command.CreateEmployeeCommand;
import com.trong.employeeservice.command.command.DeletedEmployeeCommand;
import com.trong.employeeservice.command.command.UpdateEmployeeCommand;
import com.trong.employeeservice.command.model.CreateEmployeeModel;
import com.trong.employeeservice.command.model.UpdateEmployeeModel;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee command")
@Hidden
public class EmpployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model) {
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(),
                model.getFirstName(),
                model.getLastName(),
                model.getKin(),
                false
        );
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId){
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId,model.getFirstName(),model.getLastName(),model.getKin(),model.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }

    @Hidden
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
        DeletedEmployeeCommand command = new DeletedEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }


}
