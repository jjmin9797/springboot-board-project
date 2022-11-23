package com.studio.projectboard.service;

import com.studio.projectboard.domain.type.SearchType;
import com.studio.projectboard.dto.ArticleDto;
import com.studio.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//필수 생성자 자동 생성
@RequiredArgsConstructor
@Service
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String keyword) {
        return Page.empty();
    }
    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long articleId) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(long articleId) {

    }

    public void deleteArticle(long articleId) {

    }
}
