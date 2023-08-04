package com.blog.Service.impl;

import com.blog.Service.PostService;
import com.blog.entity.Post;
import com.blog.payload.PostDTO;
import com.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {  //constructor based dependencies injection
        this.postRepo = postRepo;

    }


    @Override
    public PostDTO savePost(PostDTO postDTO) {
        Post post = mapToEntity(postDTO); //important

        Post savedPost = postRepo.save(post); //savedPost  give content which saved in database

        PostDTO dto = mapTodto(savedPost); // importANT

        return dto;
    }

    private PostDTO mapTodto(Post savedPost) {
         PostDTO dto= new PostDTO();
        dto.setId(savedPost.getId());
         dto.setTitle(savedPost.getTitle());
         dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());
        return dto;

    }

    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post(); // convert dto obj to post obj value transfer
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setTitle(post.getTitle());
        return post;
    }

}