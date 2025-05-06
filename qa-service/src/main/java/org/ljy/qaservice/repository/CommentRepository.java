package org.ljy.qaservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.ljy.common.model.entity.Comment;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    Optional<List<Comment>> findByPathStartingWith(String prefix);

    // 新增方法：按 upvoteCount 降序获取前 N 条高赞评论
    @Query("{'path': { $regex: ?0, $options: 'i' }}")
    List<Comment> findTopCommentsByPathStartingWith(String prefix, int limit);
}
