package com.trong.employeeservice.query.controller;


import com.trong.employeeservice.query.model.EmployeeResponseModel;
import com.trong.employeeservice.query.querries.GetAllEmployeeQuery;
import com.trong.employeeservice.query.querries.GetDetaiEmployeelQuerry;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Slf4j
public class EmployeeQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(@RequestParam(required = false, defaultValue = "false") Boolean isDisciplined){
        log.info("Calling to getAllEmployee");
        return queryGateway
                .query(new GetAllEmployeeQuery(isDisciplined), ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployeeDetail(@PathVariable String employeeId) {
        EmployeeResponseModel result =  queryGateway.query(
                new GetDetaiEmployeelQuerry(employeeId),
                ResponseTypes.instanceOf(EmployeeResponseModel.class))
                .join();
        return result;
    }
}
