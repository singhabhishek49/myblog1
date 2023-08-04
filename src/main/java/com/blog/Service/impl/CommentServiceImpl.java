package com.blog.Service.impl;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import exceptions.ResourceNotFoundException;
import com.blog.Service.CommentService;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepo;
    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo,CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo=commentRepo;
    }


    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException(postId)
        );
        Comment comment= new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getBody());
        comment.setBody(commentDto.getBody());

        comment.setPost(post); // line signifyes that comment is of this post and the post belongs to postId

        Comment savecomment = commentRepo.save(comment);

        CommentDto commentDto1= new CommentDto();

        commentDto1.setId(savecomment.getId());
        commentDto1.setName(savecomment.getName());
        commentDto1.setEmail(savecomment.getEmail());
        commentDto1.setBody(savecomment.getBody());

        return commentDto1;
    }

    public List<CommentDto> findCommentByPostId(long postId){

        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        List<Comment> comments = commentRepo.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public void deleteCommentById(long postId, long id) {
        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        Comment comment =commentRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
        commentRepo.deleteById(id);
    }

    @Override
    public CommentDto getCommentById(long postId, long id) {
        postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException(postId)
        );
        Comment comment =commentRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(id)
        );
        CommentDto commentDto = mapToDto(comment);

        return commentDto;
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException( postId));

        // retrieve comment by id
        Comment comment = commentRepo.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException(commentId));


        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepo.save(comment);
        return mapToDto(updatedComment);
    }




    CommentDto mapToDto(Comment comment)
    {
        CommentDto dto= new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setBody(comment.getBody());

        return dto;
    }
}
