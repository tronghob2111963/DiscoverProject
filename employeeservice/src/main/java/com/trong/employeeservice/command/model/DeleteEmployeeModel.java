package com.trong.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;

public class DeleteEmployeeModel {

    @NotBlank(message = "Id is mandatory")
    private String Id;
}
