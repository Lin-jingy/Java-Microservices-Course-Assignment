package org.ljy.common.service;

import java.util.List;

import org.ljy.common.model.dto.QuestionCreateDTO;
import org.ljy.common.model.dto.QuestionDTO;
import org.ljy.common.model.entity.Question;
import org.ljy.common.model.vo.QuestionVO;

public interface QuestionService {
    QuestionVO createQuestion(QuestionCreateDTO questionDTO, String userId);
    QuestionVO getQuestionById(String id);
    Question updateQuestion(QuestionDTO questionDTO, String id, String adminName, String adminPassword);
    long count();
    List<Question> findMostPopularQuestions(int limit);
    List<QuestionVO> findQuestionByPath(String path);
    List<QuestionVO> getQuestionsByTag(String tag);
    Question getQuestionByTitle(String title);
}
