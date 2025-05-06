package org.ljy.qaservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.ljy.qaservice.repository.CommentRepository;
import org.ljy.common.model.dto.CommentCreateDTO;
import org.ljy.common.model.entity.Comment;
import org.ljy.common.model.vo.CommentVO;
import org.ljy.common.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
@DubboService
@RequiredArgsConstructor
public class CommentImpl implements CommentService {
    private final CommentRepository commentRepository;

    public Comment createComment(CommentCreateDTO commentCreateDTO, String userId) {
        Comment comment = new Comment();
        comment.setPath(commentCreateDTO.getPath() + "/" + commentCreateDTO.getType() + "-" + comment.getId());
        BeanUtils.copyProperties(commentCreateDTO, comment);
        return commentRepository.save(comment);
    }

    public CommentVO findCommentById(String id) {
        Comment comment =  commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not exist"));
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment, commentVO);
        return commentVO;
    }

    public List<CommentVO> findCommentByPath(String path) {
        return commentRepository.findByPathStartingWith(path).orElse(new ArrayList<>()).stream().map(comment -> {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            return commentVO;
        }).toList();
    }

    public void addLikes(String commentId, String UserId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not exist"));
        var upvote = comment.getUpvote();
        if(upvote.contains(UserId)) throw new RuntimeException("User already upvote");
        upvote.add(UserId);
        comment.setUpvoteCount(upvote.size());
        commentRepository.save(comment);
    }

    public void deleteComment(String commentId, String userId, String permission) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not exist"));
        if(!comment.getAuthorId().equals(userId) && !permission.equals("admin")) throw new RuntimeException("Permission denied");
        commentRepository.delete(comment);
    }

    public List<Comment> getTopCommentsByPath(String path, int limit) {
        return commentRepository.findTopCommentsByPathStartingWith(path, limit);
    }
}
