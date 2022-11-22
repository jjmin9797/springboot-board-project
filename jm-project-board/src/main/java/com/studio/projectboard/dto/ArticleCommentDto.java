package com.studio.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.studio.projectboard.domain.ArticleComment} entity
 */
public record ArticleCommentDto(LocalDateTime createAt,
                                String createBy,
                                LocalDateTime modifiedAt,
                                String modifiedBy,
                                String content) {

    public static ArticleCommentDto of(LocalDateTime createAt, String createBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(createAt, createBy, modifiedAt, modifiedBy, content);
    }
}