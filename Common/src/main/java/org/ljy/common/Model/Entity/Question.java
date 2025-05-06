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

@Document(collection = "questions")
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Question implements Serializable {
    @Id
    private String id;
    @Indexed
    private String title;
    @Indexed
    private String content;
    @Indexed
    private List<String> tags;
    @Indexed
    private String authorId;
    private Date createdAt;
    @Indexed
    private Date updatedAt;
    @Indexed
    private Integer viewCount;
    @Indexed
    private String path;

    public Question() {
        this.id = GenObjectId.getRandObjectId();
        this.title = "";
        this.content = "";
        this.tags = new ArrayList<>();
        this.authorId = "";
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.viewCount = 0;
        this.path = "question-" + this.id;
    }

}