package com.trong.employeeservice.command.aggregate;


import com.trong.employeeservice.command.command.CreateEmployeeCommand;
import com.trong.employeeservice.command.command.DeletedEmployeeCommand;
import com.trong.employeeservice.command.command.UpdateEmployeeCommand;
import com.trong.employeeservice.command.event.EmployeeCreatedEvent;
import com.trong.employeeservice.command.event.EmployeeDeletedEvent;
import com.trong.employeeservice.command.event.EmployeeUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {

    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent();
        BeanUtils.copyProperties(command,employeeCreatedEvent);
        AggregateLifecycle.apply(employeeCreatedEvent);
    }


    @CommandHandler
    public void handle(UpdateEmployeeCommand command){
        EmployeeUpdatedEvent event = new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeletedEmployeeCommand command){
        EmployeeDeletedEvent event = new EmployeeDeletedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }




    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        this.id = event.getId();
    }
}
