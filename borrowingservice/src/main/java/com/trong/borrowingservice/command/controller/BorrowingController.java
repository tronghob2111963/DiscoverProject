package com.trong.borrowingservice.command.controller;


import com.trong.borrowingservice.command.command.CreateBorrowingCommand;
import com.trong.borrowingservice.command.model.BorrowingCreateModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String CreateBorrowing(@RequestBody BorrowingCreateModel model) {
        CreateBorrowingCommand command = new CreateBorrowingCommand(
                UUID.randomUUID().toString(),
                model.getBookId(),
                model.getEmployeeId(),
                new Date()
        );
        return commandGateway.sendAndWait(command);

    }
}
