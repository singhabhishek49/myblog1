package com.blog.Service;


import com.blog.entity.Post;
import com.blog.payload.PostDTO;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    PostDTO savePost(PostDTO postDTO);
}
