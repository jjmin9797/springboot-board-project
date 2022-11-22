package com.studio.projectboard.service;

import com.studio.projectboard.dto.ArticleCommentDto;
import com.studio.projectboard.dto.ArticleDto;
import com.studio.projectboard.repository.ArticleCommentRepository;
import com.studio.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(long id) {
        return List.of();
    }
}
