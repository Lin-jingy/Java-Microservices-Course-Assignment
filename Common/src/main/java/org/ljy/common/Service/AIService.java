package org.ljy.common.service;

import java.util.List;

import org.ljy.common.model.entity.Comment;
import org.ljy.common.model.entity.Question;

public interface AIService {
    List<String> getAIKnowledge(Question question);
    int getAIDifficulty(Question question);
    double getAIScore(Question question, Comment comment);
} 
