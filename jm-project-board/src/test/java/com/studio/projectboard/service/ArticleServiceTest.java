package com.studio.projectboard.service;

import com.studio.projectboard.domain.Article;
import com.studio.projectboard.domain.type.SearchType;
import com.studio.projectboard.dto.ArticleDto;
import com.studio.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("비즈니스 로직 - 게시판")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        //given

        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword");

        //then
        assertThat(articles)
                .isNotNull();

    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        //given

        //when
        ArticleDto article = sut.searchArticle(1L);

        //then
        assertThat(article)
                .isNotNull();

    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticles() {
        //given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(),"jm","title","content","java");
        BDDMockito.given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);

        //when
        sut.saveArticle(dto);

        //then
        BDDMockito.then(articleRepository).should().save(BDDMockito.any(Article.class));

    }

}