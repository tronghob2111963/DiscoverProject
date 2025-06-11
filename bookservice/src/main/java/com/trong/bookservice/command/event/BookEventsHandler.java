package com.trong.bookservice.command.event;


import com.trong.bookservice.command.data.Book;
import com.trong.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventsHandler {


    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event){
        Optional<Book> oldbook = bookRepository.findById(event.getId());
        oldbook.ifPresent(book -> {
           book.setName(event.getName());
           book.setAuthor(event.getAuthor());
           book.setIsReady(event.getIsReady());

           bookRepository.save(oldbook.get());
       });
    }

    @EventHandler
    public void on(BookDeletedEvent event){
        Optional<Book> oldbook = bookRepository.findById(event.getId());
        //kiem tra book co ton tai hay khong
        // neu ton tai thi xoa
        oldbook.ifPresent(book -> bookRepository.delete(book));
    }
}
