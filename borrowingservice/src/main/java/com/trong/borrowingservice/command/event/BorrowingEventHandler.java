package com.trong.borrowingservice.command.event;


import com.trong.borrowingservice.command.data.Borrowing;
import com.trong.borrowingservice.command.data.BorrowingRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BorrowingEventHandler {
    @Autowired
    private BorrowingRepository borrowingRepository;


    //xu ly event
    @EventHandler
    public void on(BorrowingCreatedEvent event) {
        Borrowing model = new Borrowing();
        model.setId(event.getId());
        model.setBookId(event.getBookId());
        model.setEmployeeId(event.getEmployeeId());
        model.setBorrowingDate(event.getBorrowingDate());
        borrowingRepository.save(model);
    }

    @EventHandler
    public void on(BorrowingDeletedEvent event) {
        Optional<Borrowing> model = borrowingRepository.findById(event.getId());
        model.ifPresent(borrowing -> borrowingRepository.delete(borrowing));
    }
}
