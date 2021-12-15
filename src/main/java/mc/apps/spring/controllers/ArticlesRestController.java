package mc.apps.spring.controllers;

import mc.apps.spring.jpa.Article;
import mc.apps.spring.jpa.ArticleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesRestController {
    final ArticleRepository repository;
    public ArticlesRestController(ArticleRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value="/rest/articles")
    public Iterable<Article> list(){
        return repository.findAll();
    }

}
