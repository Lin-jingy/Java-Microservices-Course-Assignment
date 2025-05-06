package org.ljy.qaservice.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.ljy.common.model.dto.CommentCreateDTO;
import org.ljy.common.model.entity.Comment;
import org.ljy.common.model.vo.CommentVO;
import org.ljy.common.service.CommentService;
import org.ljy.common.util.jwt.JWTUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @DubboReference
    private CommentService commentService;

    @PostMapping("/create")
    public Comment createComment(@RequestHeader("token") String token, @RequestBody CommentCreateDTO commentCreateDTO) {
        String userId = JWTUtils.verify(token).get("userId");
        return commentService.createComment(commentCreateDTO, userId);
    }

    @GetMapping("/get")
    public CommentVO findCommentById(@RequestBody String id) {
        return commentService.findCommentById(id);
    }

    @GetMapping("/getByPath")
    public List<CommentVO> findCommentByPath(@RequestBody String path) {
        return commentService.findCommentByPath(path);
    }

    @PostMapping("/addLikes")
    public void addLikes(@RequestHeader("token") String token, @RequestBody String commentId) {
        commentService.addLikes(commentId, JWTUtils.verify(token).get("id"));
    }

    @PostMapping("/delete")
    public void deleteComment(@RequestHeader("token") String token, @RequestBody String commentId) {
        commentService.deleteComment(commentId, JWTUtils.verify(token).get("id"), JWTUtils.verify(token).get("permission"));
    }

    @PostMapping("/getAIScore")
    public double getAIScore(@RequestBody String commentId) {
        return commentService.findCommentById(commentId).getAiScore();
    }


}