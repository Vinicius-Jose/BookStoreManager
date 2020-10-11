package com.viniciusnogueira.book.storemanager.repository;

import com.viniciusnogueira.book.storemanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
