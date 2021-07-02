package com.mike.springboot2.webservice.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void 메인페이지_로딩() {
        String body = this.testRestTemplate.getForObject("/", String.class);
        System.out.println(body);

        assertTrue(body.contains("스프링 부트로 시작하는 웹 서비스"));

    }

}
