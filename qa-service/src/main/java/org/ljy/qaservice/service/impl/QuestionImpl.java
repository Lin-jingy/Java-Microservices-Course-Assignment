package org.ljy.qaservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.ljy.common.model.dto.QuestionCreateDTO;
import org.ljy.common.model.dto.QuestionDTO;
import org.ljy.common.model.entity.Question;
import org.ljy.common.model.entity.User;
import org.ljy.common.model.vo.QuestionVO;
import org.ljy.common.service.QuestionService;
import org.ljy.qaservice.repository.QuestionRepository;
import org.ljy.userservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@DubboService
@RequiredArgsConstructor
public class QuestionImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionVO createQuestion(QuestionCreateDTO questionCreateDTO, String userId) {
        Question question = new Question();
        BeanUtils.copyProperties(questionCreateDTO, question);
        question.setAuthorId(userId);
        questionRepository.save(question);
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        return questionVO;
    }

    public QuestionVO getQuestionById(String id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not exist"));
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        return questionVO;
    }

    public Question updateQuestion(QuestionDTO questionDTO, String id, String adminName, String adminPassword) {
        User user = userRepository.findByUsername(adminName)
                .orElseThrow(() -> new RuntimeException("User not exist"));
        if(!user.getPassword().equals(adminPassword)) {
            throw new RuntimeException("Admin password error");
        }
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not exist"))
                .setTitle(questionDTO.getTitle())
                .setContent(questionDTO.getContent())
                .setTags(questionDTO.getTags())
                .setUpdatedAt(new Date());
        return questionRepository.save(question);
    }

    public long count() {
        return questionRepository.count();
    }

    public List<Question> findMostPopularQuestions(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return questionRepository.findTopByOrderByViewCountDesc(pageable);
    }

    public List<QuestionVO> findQuestionByPath(String path) {
        return questionRepository.findByPathStartingWith(path).stream().map(question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            return questionVO;
        }).toList();
    }

    public List<QuestionVO> getQuestionsByTag(String tag) {
        return questionRepository.findByTagsContaining(tag).stream().map(Question -> {
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(Question, questionVO);
            return questionVO;
        }).toList();
    }

    public Question getQuestionByTitle(String title) {
        return questionRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Question not exist"));
    }
}
