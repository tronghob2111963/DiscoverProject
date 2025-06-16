package com.trong.employeeservice.query.projection;

import com.trong.commonservice.model.EmployeeResponseCommonModel;
import com.trong.employeeservice.command.data.Employee;
import com.trong.employeeservice.command.data.EmployeeRepository;
import com.trong.employeeservice.query.model.EmployeeResponseModel;
import com.trong.employeeservice.query.querries.GetAllEmployeeQuery;
import com.trong.commonservice.querries.GetDetailEmployeeQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeProjection {

    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        List<Employee> listEmployee = employeeRepository.findAllByIsDisciplined(query.getIsDisciplined());
        return listEmployee.stream().map(employee -> {
            EmployeeResponseModel model = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, model);
            return model;
        }).toList();
    }

    @QueryHandler
    public EmployeeResponseCommonModel handle(GetDetailEmployeeQuery query) throws Exception {
        Employee employee = employeeRepository.findById(query.getId()).orElseThrow(() -> new Exception("Employee not found"));
        EmployeeResponseCommonModel model = new EmployeeResponseCommonModel();
        BeanUtils.copyProperties(employee, model);
        return model;
    }

}
