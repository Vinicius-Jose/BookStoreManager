package controller;

import com.viniciusnogueira.book.storemanager.controller.BookController;
import com.viniciusnogueira.book.storemanager.dto.BookDTO;
import com.viniciusnogueira.book.storemanager.dto.MessageResponseDTO;
import com.viniciusnogueira.book.storemanager.service.BookService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import utils.BookUtils;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMVC;

    @Mock
    private BookService bookService;
    
    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp(){
        mockMVC = MockMvcBuilders.standaloneSetup(bookController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testWhenPOSTisCalledThenABookShouldBeCreated() {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder().message("Book created with ID" + bookDTO.getId())
                .build();

        Mockito.when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

        try {
            mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(BookUtils.asJsonString(bookDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWhenPOSTWithInvalidISBNIsCalledThenBadRequestShouldBeReturned() {
        BookDTO bookDTO = BookUtils.createFakeBookDTO();
        bookDTO.setIsbn("invalid isbn");



        try {
            mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/books")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(BookUtils.asJsonString(bookDTO)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
