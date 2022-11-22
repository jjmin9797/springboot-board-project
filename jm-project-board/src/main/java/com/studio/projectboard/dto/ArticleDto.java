package com.studio.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.studio.projectboard.domain.Article} entity
 */
public record ArticleDto(LocalDateTime createAt,
                         String createBy,
                         String title,
                         String content,
                         String hashtag){

    public static ArticleDto of(LocalDateTime createAt, String createBy, String title, String content, String hashtag) {
        return new ArticleDto(createAt,createBy,title,content,hashtag);
    }
}