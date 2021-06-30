package com.mike.springboot2.webservice.web;

import com.mike.springboot2.webservice.domain.posts.Posts;
import com.mike.springboot2.webservice.domain.posts.PostsRepository;
import com.mike.springboot2.webservice.web.dto.PostsSaveRequestDto;
import com.mike.springboot2.webservice.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int randomPort;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @AfterEach
    public void tear_down() {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() {
        //given
        String post_title = "title";
        String post_content = "content";

        PostsSaveRequestDto postsSaveRequestDto = PostsSaveRequestDto.builder()
                .post_title(post_title)
                .post_content(post_content)
                .post_author("author")
                .build();

        String url = "http://localhost:" + randomPort + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, postsSaveRequestDto, Long.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertTrue(responseEntity.getBody() > 0L);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        assertEquals(posts.getPost_title(), post_title);
        assertEquals(posts.getPost_content(), post_content);
        assertEquals(posts.getPost_author(), "author");
    }

    @Test
    public void Posts_수정된다() {
        //given
        Posts savePosts = postsRepository.save(Posts.builder()
                .post_title("title")
                .post_content("content")
                .post_author("author")
                .build());

        Long postId = savePosts.getPost_id();

        String expectedPostTitle = "title2";
        String expectedPostContent = "content2";

        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                .post_title(expectedPostTitle)
                .post_content(expectedPostContent)
                .build();

        String url = "http://localhost:" + randomPort + "/api/v1/posts/" + postId;

        System.out.println(url);

        HttpEntity<PostsUpdateRequestDto> postsUpdateRequestDtoHttpEntity = new HttpEntity<>(postsUpdateRequestDto);
        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, postsUpdateRequestDtoHttpEntity, Long.class);

        //then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertTrue(responseEntity.getBody() > 0L);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        assertEquals(posts.getPost_title(), expectedPostTitle);
        assertEquals(posts.getPost_content(), expectedPostContent);
    }
}
