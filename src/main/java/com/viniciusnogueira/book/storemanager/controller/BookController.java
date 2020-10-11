package com.viniciusnogueira.book.storemanager.controller;

import com.viniciusnogueira.book.storemanager.dto.MessageResponseDTO;
import com.viniciusnogueira.book.storemanager.entity.Book;
import com.viniciusnogueira.book.storemanager.repository.BookRepository;
import com.viniciusnogueira.book.storemanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookservice;

    @Autowired
    public BookController(BookRepository bookservice) {
        this.bookservice = bookservice;
    }

    @PostMapping
    public MessageResponseDTO create (@RequestBody Book book){

        return  bookservice.create(book);
    }

}
