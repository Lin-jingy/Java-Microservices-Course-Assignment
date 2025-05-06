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
public class CommentVO  implements Serializable {
    private String type;
    private String content;
    private String authorId;
    private Date createdAt;
    private Date updatedAt;
    private List<String> upvote;
    private Integer upvoteCount;
    private Double aiScore;
    private String path;
}
