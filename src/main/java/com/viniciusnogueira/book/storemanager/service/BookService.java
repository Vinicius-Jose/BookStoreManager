package com.viniciusnogueira.book.storemanager.service;

import com.viniciusnogueira.book.storemanager.dto.BookDTO;
import com.viniciusnogueira.book.storemanager.dto.MessageResponseDTO;
import com.viniciusnogueira.book.storemanager.entity.Book;
import com.viniciusnogueira.book.storemanager.exception.BookNotFoundException;
import com.viniciusnogueira.book.storemanager.mapper.BookMapper;
import com.viniciusnogueira.book.storemanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private final BookMapper bookMapper = BookMapper.INSTANCE;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public MessageResponseDTO create(BookDTO bookDTO){
        
        Book bookToSave = bookMapper.toModel(bookDTO);

        Book savedBook = bookRepository.save(bookToSave);
        return  MessageResponseDTO.builder().message("Book created with ID " + savedBook.getId() + " Author name " + savedBook.getAuthor().getName()).build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDTO(book);
    }
}
