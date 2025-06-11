package com.trong.bookservice.query.projection;


import com.trong.bookservice.command.data.Book;
import com.trong.bookservice.command.data.BookRepository;
import com.trong.bookservice.query.model.BookResponseModel;
import com.trong.bookservice.query.queries.GetAllBookeQuerry;
import com.trong.bookservice.query.queries.GetBookDetailQuerry;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> getAllBooks(GetAllBookeQuerry query) {
        List<Book> list = bookRepository.findAll();
        List<BookResponseModel> listBookResponse = new ArrayList<>();
        list.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book,model);
            listBookResponse.add(model);
        });
        return listBookResponse;
    }

    @QueryHandler
    public BookResponseModel getBookDetail(GetBookDetailQuerry query) throws Exception {
        BookResponseModel bookResponsemodel = new BookResponseModel();
        Book book = bookRepository.findById(query.getId()).orElseThrow(() -> new Exception("Book not found"));
        BeanUtils.copyProperties(book,bookResponsemodel);
        return bookResponsemodel;
    }

}
