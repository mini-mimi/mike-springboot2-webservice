package com.mike.springboot2.webservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mike.springboot2.webservice.domain.posts.Posts;
import com.mike.springboot2.webservice.domain.posts.PostsRepository;
import com.mike.springboot2.webservice.web.dto.PostsResponseDto;
import com.mike.springboot2.webservice.web.dto.PostsSaveRequestDto;
import com.mike.springboot2.webservice.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int randomPort;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tear_down() {
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void Posts_등록된다() throws Exception {
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
        //ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, postsSaveRequestDto, Long.class);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(postsSaveRequestDto)))
                .andExpect(status().isOk());

        //then
        //assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //assertTrue(responseEntity.getBody() > 0L);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        assertEquals(posts.getPost_title(), post_title);
        assertEquals(posts.getPost_content(), post_content);
        assertEquals(posts.getPost_author(), "author");
    }

    @Test
    @WithMockUser("USER")
    public void Posts_수정된다() throws  Exception{
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

        //HttpEntity<PostsUpdateRequestDto> postsUpdateRequestDtoHttpEntity = new HttpEntity<>(postsUpdateRequestDto);
        //when
        //ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.POST, postsUpdateRequestDtoHttpEntity, Long.class);
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(postsUpdateRequestDto)))
                .andExpect(status().isOk());

        //then
        //assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //assertTrue(responseEntity.getBody() > 0L);

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);
        assertEquals(posts.getPost_title(), expectedPostTitle);
        assertEquals(posts.getPost_content(), expectedPostContent);
    }

    @Test
    @WithMockUser("USER")
    public void Posts_조회된다() throws Exception {
        //given
        String post_title = "title";
        String post_conent = "content";
        String post_author = "author";

        Posts posts = postsRepository.save(Posts.builder()
                .post_title(post_title)
                .post_content(post_conent)
                .post_author(post_author)
                .build());

        Long postId = posts.getPost_id();

        String url = "http://localhost:" + randomPort + "/api/v1/posts/" + postId;
        //URI uri = new URI(url);

        //when
        //ResponseEntity<PostsResponseDto> responseEntity = testRestTemplate.getForEntity(uri, PostsResponseDto.class);
        mockMvc.perform(get(url))
                .andExpect(status().isOk());

        //then
        //assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        //assertTrue(responseEntity.getBody().getPost_id() > 0L);
        //assertEquals(responseEntity.getBody().getPost_title(), post_title);
        //assertEquals(responseEntity.getBody().getPost_content(), post_conent);
        //assertEquals(responseEntity.getBody().getPost_author(), post_author);

    }
}
