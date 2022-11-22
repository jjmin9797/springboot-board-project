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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createBy")
})
@Entity
public class Article extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @Setter
    private UserAccount userAccount;

    @OrderBy("createAt DESC")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @Setter @Column(nullable = false)
    private String title; //제목

    @Setter @Column(nullable = false, length = 10000)
    private String content; // 본문

    @Setter
    private String hashtag; //해시태그

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    protected Article() {}

    private Article(String title, String content, String hashtag, UserAccount userAccount) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.userAccount = userAccount;
    }
    public static Article of(String title, String content, String hashtag, UserAccount userAccount) {
        return new Article(title,content,hashtag,userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
