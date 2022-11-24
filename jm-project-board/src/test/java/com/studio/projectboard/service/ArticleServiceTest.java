package com.studio.projectboard.service;

import com.studio.projectboard.domain.Article;
import com.studio.projectboard.domain.UserAccount;
import com.studio.projectboard.domain.type.SearchType;
import com.studio.projectboard.dto.ArticleDto;
import com.studio.projectboard.dto.ArticleWithCommentsDto;
import com.studio.projectboard.dto.UserAccountDto;
import com.studio.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DisplayName("비즈니스 로직 - 게시판")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        //given
        Pageable pageable = Pageable.ofSize(20);
        BDDMockito.given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        //when
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        //then
        assertThat(articles).isEmpty();
        BDDMockito.then(articleRepository).should().findAll(pageable);

    }

    @DisplayName("검색어와 함께 게시글을 검색하면, 게시글 페이지를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticlePage() {
        //given
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        BDDMockito.given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        //when
        Page<ArticleDto> dto = sut.searchArticles(searchType, searchKeyword, pageable);

        //then
        assertThat(dto).isEmpty();
        BDDMockito.then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticle_thenReturnsArticle() {
        //given
        long articleId = 1L;
        Article article = createArticle();
        BDDMockito.given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        //when
        ArticleWithCommentsDto dto = sut.getArticle(articleId);

        //then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        BDDMockito.then(articleRepository).should().findById(articleId);
    }

    @DisplayName("없는 게시글을 조회하면, 예외를 던진다.")
    @Test
    void givenNonexistentArticleId_whenSearchingArticle_thenThrowsException() {
        //given
        long articleId = 0L;
        BDDMockito.given(articleRepository.findById(articleId)).willReturn(Optional.empty());

        //when
        Throwable t = catchThrowable(() -> sut.getArticle(articleId));

        //then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class);
        BDDMockito.then(articleRepository).should().findById(articleId);
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {

        //given
        ArticleDto dto = createArticleDto();
        BDDMockito.given(articleRepository.save(BDDMockito.any(Article.class))).willReturn(createArticle());

        //when
        sut.saveArticle(dto);

        //then
        BDDMockito.then(articleRepository).should().save(BDDMockito.any(Article.class));

    }


    @DisplayName("게시글의 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdatesArticle() {
        //given
        Article article = createArticle();
        ArticleDto dto = createArticleDto("update title","update content","#내돈내산");
        BDDMockito.given(articleRepository.getReferenceById(dto.id())).willReturn(article);

        //when
        sut.updateArticle(dto);

        //then
        //hasFieldOrPropertyWithValue -> title 필드의 값이 dto.title() 의 값과 같은지?
        assertThat(article)
                .hasFieldOrPropertyWithValue("title",dto.title())
                .hasFieldOrPropertyWithValue("content",dto.content())
                .hasFieldOrPropertyWithValue("hashtag",dto.hashtag());
        BDDMockito.then(articleRepository).should().getReferenceById(dto.id());
    }
    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticles() {
//        //given
//        ArticleDto dto = ArticleDto.of(LocalDateTime.now(),"jm","title","content","java");
//        BDDMockito.given(articleRepository.save(ArgumentMatchers.any(Article.class))).willReturn(null);
//
//        //when
//        sut.saveArticle(dto);
//
//        //then
//        BDDMockito.then(articleRepository).should().save(BDDMockito.any(Article.class));

    }



    private UserAccount createUserAccount() {
        return UserAccount.of(
                "uno",
                "password",
                "uno@email.com",
                "Uno",
                null
        );
    }

    private Article createArticle() {
        return Article.of(
                createUserAccount(),
                "title",
                "content",
                "#java"
        );
    }

    private ArticleDto createArticleDto() {
        return createArticleDto("title", "content", "#java");
    }

    private ArticleDto createArticleDto(String title, String content, String hashtag) {
        return ArticleDto.of(1L,
                createUserAccountDto(),
                title,
                content,
                hashtag,
                LocalDateTime.now(),
                "Uno",
                LocalDateTime.now(),
                "Uno");
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                1L,
                "uno",
                "password",
                "uno@mail.com",
                "Uno",
                "This is memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

}