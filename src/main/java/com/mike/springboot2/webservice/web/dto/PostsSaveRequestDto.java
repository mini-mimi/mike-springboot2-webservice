package com.mike.springboot2.webservice.web.dto;

import com.mike.springboot2.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String post_title;
    private String post_content;
    private String post_author;

    @Builder
    public PostsSaveRequestDto(String post_title, String post_content, String post_author) {
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_author = post_author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .post_title(post_title)
                .post_content(post_content)
                .post_author(post_author)
                .build();
    }

}
