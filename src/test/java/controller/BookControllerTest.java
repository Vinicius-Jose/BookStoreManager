package controller;

import com.viniciusnogueira.book.storemanager.controller.BookController;
import com.viniciusnogueira.book.storemanager.dto.BookDTO;
import com.viniciusnogueira.book.storemanager.dto.MessageResponseDTO;
import com.viniciusnogueira.book.storemanager.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
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
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder().message("Book create with OD" +
                bookDTO.getId()).build();
                
    }
}
