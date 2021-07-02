package com.mike.springboot2.webservice.web;

import com.mike.springboot2.webservice.domain.posts.Posts;
import com.mike.springboot2.webservice.service.posts.PostsService;
import com.mike.springboot2.webservice.web.dto.PostsResponseDto;
import com.mike.springboot2.webservice.web.dto.PostsSaveRequestDto;
import com.mike.springboot2.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto) {
        return postsService.save(postsSaveRequestDto);
    }

    @PostMapping("/api/v1/posts/{post_id}")
    public Long update(@PathVariable Long post_id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto) {
        return postsService.update(post_id, postsUpdateRequestDto);
    }

    @GetMapping("/api/v1/posts/{post_id}")
    public PostsResponseDto findById(@PathVariable Long post_id) {
        return postsService.findById(post_id);
    }

    @DeleteMapping("/api/v1/posts/{post_id}")
    public Long delete(@PathVariable Long post_id) {
        postsService.delete(post_id);
        return post_id;
    }
}
