package com.hk.restfulwebservice.controller;

import com.hk.restfulwebservice.model.Post;
import com.hk.restfulwebservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/users/{userId}/posts")
    public List<Post> getPostByUser(@PathVariable Integer userId) {
        return postService.getPostsByUser(userId);
    }

    @PostMapping(path = "/users/{userId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Integer userId, @RequestBody Post post) {
        Post savedPost = postService.createPost(userId, post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedPost);
    }
}
