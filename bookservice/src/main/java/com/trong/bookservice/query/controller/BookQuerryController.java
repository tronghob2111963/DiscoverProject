package com.trong.bookservice.query.controller;


import com.trong.bookservice.query.model.BookResponseModel;
import com.trong.bookservice.query.queries.GetAllBookeQuerry;
import com.trong.commonservice.model.BookResponseCommonModel;
import com.trong.commonservice.querries.GetBookDetailQuery;
import com.trong.commonservice.services.KafkaService;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQuerryController {

    @Autowired
    private QueryGateway queryGateway;

    @Autowired
    private KafkaService kafkaService;

    @GetMapping
    public List<BookResponseModel> getAllBooks() {
        GetAllBookeQuerry query = new GetAllBookeQuerry();
        List<BookResponseModel> reslut =  queryGateway
                .query(query, ResponseTypes.multipleInstancesOf(BookResponseModel.class))
                .join();
        return reslut;
    }

    @GetMapping("/{bookId}")
    public BookResponseCommonModel getBookDetail(@PathVariable String bookId) {
        GetBookDetailQuery query = new GetBookDetailQuery(bookId);
        return queryGateway
                .query(query, ResponseTypes.instanceOf(BookResponseCommonModel.class))
                .join();

    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message) {
        kafkaService.sendMessage("test", message);
    }
}
