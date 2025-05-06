package org.ljy.common.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import org.ljy.common.util.GenObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Comment")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Comment implements Serializable {
    @Id
    private String id;
    private String type;
    private String content;
    @Indexed
    private String authorId;
    private Date createdAt;
    @Indexed
    private Date updatedAt;
    private List<String> upvote;
    private Integer upvoteCount;
    private Double aiScore;
    private Double []embedding;
    @Indexed
    private String path;

    public Comment() {
        this.id = GenObjectId.getRandObjectId();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.upvote = new ArrayList<>();
        this.upvoteCount = 0;
    }
}
