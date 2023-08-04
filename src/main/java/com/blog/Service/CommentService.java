package com.blog.Service;
import com.blog.entity.Comment;
import com.blog.payload.CommentDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);


    List<CommentDto> findCommentByPostId(long postId);

    void deleteCommentById(long postId, long id);

    CommentDto getCommentById(long postId, long id);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
}
