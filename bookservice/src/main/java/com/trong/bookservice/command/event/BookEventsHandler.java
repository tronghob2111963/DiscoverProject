package com.trong.bookservice.command.event;


import com.trong.bookservice.command.data.Book;
import com.trong.bookservice.command.data.BookRepository;
import com.trong.commonservice.event.BookRollBackStatusEvent;
import com.trong.commonservice.event.BookUpdateStatusEvent;
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
        Optional<Book> oldBook = bookRepository.findById(event.getId());
        oldBook.ifPresent(book -> {
            book.setName(event.getName());
            book.setAuthor(event.getAuthor());
            book.setIsReady(event.getIsReady());
            bookRepository.save(book);
        });

    }

    @EventHandler
    public void on(BookUpdateStatusEvent event){
        Optional<Book> oldBook = bookRepository.findById(event.getBookId());
        oldBook.ifPresent(book -> {
            book.setIsReady(event.getIsReady());
            bookRepository.save(book);
        });
    }

    @EventHandler
    public void on(BookDeletedEvent event){
        Optional<Book> oldBook = bookRepository.findById(event.getId());
        oldBook.ifPresent(book -> bookRepository.delete((book)));
    }

    @EventHandler
    public void on(BookRollBackStatusEvent event){
        Optional<Book> oldBook = bookRepository.findById(event.getBookId());
        oldBook.ifPresent(book -> {
            book.setIsReady(event.getIsReady());
            bookRepository.save(book);
        });
    }
}
