package service;

import com.viniciusnogueira.book.storemanager.dto.BookDTO;
import com.viniciusnogueira.book.storemanager.entity.Book;
import com.viniciusnogueira.book.storemanager.exception.BookNotFoundException;
import com.viniciusnogueira.book.storemanager.repository.BookRepository;
import com.viniciusnogueira.book.storemanager.service.BookService;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.BookUtils;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
        Book expectedFoundBook = BookUtils.createFakeBook();
        Mockito.when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDto = bookService.findById(expectedFoundBook.getId());

        Assertions.assertEquals(expectedFoundBook.getName(), bookDto.getName());
        Assertions.assertEquals(expectedFoundBook.getIsbn(), bookDto.getIsbn());
        Assertions.assertEquals(expectedFoundBook.getPublisherName(), bookDto.getPublisherName());
    }


    @Test
    void whenGivenUnexistingIdThenNotFoundThrowAnException() throws BookNotFoundException {

        Long invalid = 10L;
        Mockito.when(bookRepository.findById(invalid)).thenReturn(Optional.ofNullable(null));


        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.findById(invalid));
    }
}
