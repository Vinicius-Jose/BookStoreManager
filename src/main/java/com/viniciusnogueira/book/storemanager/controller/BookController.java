package com.viniciusnogueira.book.storemanager.controller;

import com.viniciusnogueira.book.storemanager.dto.BookDTO;
import com.viniciusnogueira.book.storemanager.dto.MessageResponseDTO;
import com.viniciusnogueira.book.storemanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookservice;

    @Autowired
    public BookController(BookService bookservice) {
        this.bookservice = bookservice;
    }

    @PostMapping
    public MessageResponseDTO create (@RequestBody @Valid BookDTO bookDTO){

        return  bookservice.create(bookDTO);
    }

}
