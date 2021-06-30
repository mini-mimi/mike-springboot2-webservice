package com.mike.springboot2.webservice.service.posts;

import com.mike.springboot2.webservice.domain.posts.Posts;
import com.mike.springboot2.webservice.domain.posts.PostsRepository;
import com.mike.springboot2.webservice.web.dto.PostsResponseDto;
import com.mike.springboot2.webservice.web.dto.PostsSaveRequestDto;
import com.mike.springboot2.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto postsSaveRequestDto) {
        return postsRepository.save(postsSaveRequestDto.toEntity()).getPost_id();
    }

    @Transactional
    public Long update(Long post_id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts posts = postsRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + post_id));

        posts.update(postsUpdateRequestDto.getPost_title(), postsUpdateRequestDto.getPost_content());

        return post_id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long post_id) {
        Posts posts = postsRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + post_id));

        return new PostsResponseDto(posts);
    }
}
