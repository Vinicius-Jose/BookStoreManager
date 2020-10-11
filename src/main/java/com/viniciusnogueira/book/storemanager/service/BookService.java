package com.viniciusnogueira.book.storemanager.service;

import com.viniciusnogueira.book.storemanager.dto.MessageResponseDTO;
import com.viniciusnogueira.book.storemanager.entity.Book;
import com.viniciusnogueira.book.storemanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public MessageResponseDTO create(Book book){
        Book savedBook = bookRepository.save(book);
        return  MessageResponseDTO.builder().message("Book create with OD" + savedBook.getId()).build();
    }

}
