package com.mike.springboot2.webservice.web.dto;

import com.mike.springboot2.webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long post_id;
    private String post_title;
    private String post_content;
    private String post_author;

    public PostsResponseDto(Posts entity) {
        this.post_id = entity.getPost_id();
        this.post_title = entity.getPost_title();
        this.post_content = entity.getPost_content();
        this.post_author = entity.getPost_author();
    }
}
