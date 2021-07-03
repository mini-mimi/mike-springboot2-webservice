package com.mike.springboot2.webservice.web;

import com.mike.springboot2.webservice.config.auth.LoginUser;
import com.mike.springboot2.webservice.config.auth.dto.SessionUser;
import com.mike.springboot2.webservice.service.posts.PostsService;
import com.mike.springboot2.webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser) {

        model.addAttribute("posts", postsService.findAllDesc());

        if (sessionUser != null) {
            model.addAttribute("userName", sessionUser.getUsers_name());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto postsResponseDto = postsService.findById(id);
        model.addAttribute("post", postsResponseDto);

        return "posts-update";
    }
}
