package mc.apps.spring;

import mc.apps.spring.controllers.FrontController;
import mc.apps.spring.jpa.Article;
import mc.apps.spring.jpa.ArticleRepository;
import mc.apps.spring.jpa.ArticleService;
import mc.apps.spring.jpa.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
// @SpringBootTest // load complete application context for end to end integration testing
public class SpringBootTestsWithMockito {
    private static final Logger logger = LogManager.getLogger(SpringBootTestsWithMockito.class);

    @InjectMocks
    ArticleService service;

    @Mock
    ArticleRepository repository;

//    @BeforeEach
//    public void init(){
//
//    }

    @Test
    public void ArticleServiceTests(){
        assertNotNull(service);

        assertEquals(true, service.isOk()); // injection (repository) ok!

        List<Article> items = Arrays.asList(
                new Article(1,"Galaxy S10e",999.99f,"cool","s10e.png"),
                new Article(2,"Galaxy S10+",1099.99f,"cool+","s10.png")
        );

        when(repository.findAll()).thenReturn(items);

        logger.info(service.list());
        assertEquals(2, service.list().size());

//        when(service.list().size()).thenReturn(2);
//        assertEquals(2, service.list().size());

//        Iterable<Article> items = service.list();
//        logger.info(items);
    }

}
