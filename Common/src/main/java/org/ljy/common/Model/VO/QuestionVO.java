package org.ljy.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class QuestionVO implements Serializable {
    private String title;
    private String content;
    private List<String> tags;
    private String authorId;
    private Date createdAt;
    private Date updatedAt;
    private Integer viewCount;
    private String path;
}
