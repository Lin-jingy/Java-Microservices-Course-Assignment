package org.ljy.qaservice.repository;

import org.ljy.common.model.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    long count();
    List<Question> findTopByOrderByViewCountDesc(Pageable pageable);
    List<Question> findByPathStartingWith(String prefix);
    List<Question> findByTagsContaining(String tag);
    Optional<Question> findByTitle(String title);
}
