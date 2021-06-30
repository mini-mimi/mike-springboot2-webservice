package com.mike.springboot2.webservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String post_title;
    private String post_content;

    @Builder
    public PostsUpdateRequestDto(String post_title, String post_content) {
        this.post_title = post_title;
        this.post_content = post_content;
    }
}
