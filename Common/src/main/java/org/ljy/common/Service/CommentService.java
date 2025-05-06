package org.ljy.common.service;


import org.ljy.common.model.dto.CommentCreateDTO;
import org.ljy.common.model.entity.Comment;
import org.ljy.common.model.vo.CommentVO;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentCreateDTO commentDTO, String userId);
    CommentVO findCommentById(String id);
    List<CommentVO> findCommentByPath(String path);
    void addLikes(String commentId, String UserId);
    void deleteComment(String commentId, String userId, String permission);
    List<Comment> getTopCommentsByPath(String path, int limit);
}
