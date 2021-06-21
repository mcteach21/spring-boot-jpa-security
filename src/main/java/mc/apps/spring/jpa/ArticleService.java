package mc.apps.spring.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> list() {
        return (List<Article>) articleRepository.findAll();
    }
}
