package com.mike.springboot2.webservice.domain.posts;

import javafx.geometry.Pos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String post_title = "테스트 게시글";
        String post_content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .post_title(post_title)
                .post_content(post_content)
                .post_author("테스트 작성자")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        assertEquals(posts.getPost_title(), post_title);
        assertEquals(posts.getPost_content(), post_content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2021, 7, 1, 0, 0, 0);

        postsRepository.save(Posts.builder()
                .post_title("title")
                .post_content("content")
                .post_author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        assertTrue(posts.getCreatedDate().isAfter(localDateTime));
        assertTrue(posts.getModifedDate().isAfter(localDateTime));
    }
}
