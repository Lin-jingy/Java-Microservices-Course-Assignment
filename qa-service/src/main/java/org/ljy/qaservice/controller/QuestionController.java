package org.ljy.qaservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.ljy.common.model.dto.QuestionCreateDTO;
import org.ljy.common.model.vo.QuestionVO;
import org.ljy.common.service.QuestionService;
import org.ljy.common.util.jwt.JWTUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @DubboReference
    private QuestionService questionService;

    @PostMapping("/create")
    public QuestionVO createQuestion(@RequestHeader("token") String token, @RequestBody QuestionCreateDTO questionCreateDTO) {
        return questionService.createQuestion(questionCreateDTO, JWTUtils.verify(token).get("id"));
    }

    @GetMapping("/get")
    public QuestionVO getQuestionById(@RequestParam String id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/get/path")
    public List<QuestionVO> findQuestionByPath(@RequestParam String path) {
        return questionService.findQuestionByPath(path);
    }

    @GetMapping("/get/tag")
    public List<QuestionVO> getQuestionsByTag(@RequestParam String tag) {
        return questionService.getQuestionsByTag(tag);
    }
}
