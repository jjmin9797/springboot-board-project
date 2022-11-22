package com.studio.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
@Entity
public class ArticleComment extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //제목

    @Setter @ManyToOne(optional = false) private UserAccount userAccount;

    @Setter
    @ManyToOne(optional = false)
    private Article article; //게시글(ID)

    @Setter
    @Column(nullable = false,length = 500)
    private String content; //본문



    protected ArticleComment() {}

    private ArticleComment(Article article, String content,UserAccount userAccount) {
        this.article = article;
        this.content = content;
        this.userAccount = userAccount;
    }
    public static ArticleComment of(Article article, String content,UserAccount userAccount) {
        return new ArticleComment(article,content,userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleComment that = (ArticleComment) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
