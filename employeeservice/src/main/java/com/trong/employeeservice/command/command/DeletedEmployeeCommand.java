package com.trong.employeeservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@AllArgsConstructor
public class DeletedEmployeeCommand {

    @TargetAggregateIdentifier
    private String id;
}
