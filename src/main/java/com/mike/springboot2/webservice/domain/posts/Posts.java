package com.mike.springboot2.webservice.domain.posts;

import com.mike.springboot2.webservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(length = 500, nullable = false)
    private String post_title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String post_content;

    private String post_author;

    @Builder
    public Posts(String post_title, String post_content, String post_author) {
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_author = post_author;
    }

    public void update(String post_title, String post_content) {
        this.post_title = post_title;
        this.post_content = post_content;
    }


}
