package com.blog.controller;



import com.blog.Service.PostService;
import com.blog.payload.PostDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {


    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //http://localhost:8080/api/posts
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping   // satutes for post 201
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDto, BindingResult result) //take content from json to Dto
    {// because it will respond back in postman using Response Entity ** valid use to check validation before
        // values are store in postdto. "?" genrics is used to return all types of data string or postdto
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDTO savedDto = postService.savePost(postDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
}