package com.mike.springboot2.webservice.web.dto;

import com.mike.springboot2.webservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long post_id;
    private String post_title;
    private String post_author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.post_id = entity.getPost_id();
        this.post_title = entity.getPost_title();
        this.post_author = entity.getPost_author();
        this.modifiedDate = entity.getModifedDate();
    }
}
