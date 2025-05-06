package org.ljy.aiservice.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.ljy.common.model.entity.Comment;
import org.ljy.common.model.entity.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ljy.common.service.AIService;

import java.util.List;

@DubboService
@Service
@Slf4j
public class AIImpl implements AIService {

    private final ChatClient chatClient;

//    @DubboReference
//    private QuestionRepository questionRepository;

    @Value("${AIService.getAIKnowledgeTemplate}")
    private String getAIKnowledgeTemplate;

    @Value("${AIService.getAIDifficultyTemplate}")
    private String getAIDifficultyTemplate;

    public AIImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    private String ask(String msg) {
        return this.chatClient.prompt()
                .user(msg)
                .call()
                .content();
    }

    @Override
    public List<String> getAIKnowledge(Question question) {
        String generatedContent = ask(getAIKnowledgeTemplate + question.getTitle() + question.getContent());
        try{
            return List.of(generatedContent.split(" "));
        }catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
    @Override
    public int getAIDifficulty(Question question) {
        String generatedContent = ask(getAIDifficultyTemplate + question.getTitle() + question.getContent());
        try {
            return Integer.parseInt(generatedContent);
        } catch(Exception e) {
            log.error(e.toString());
            return -1;
        }
    }

    @Override
    public double getAIScore(Question question, Comment comment) {
        // TODO
        return 0.0;
    }
}
