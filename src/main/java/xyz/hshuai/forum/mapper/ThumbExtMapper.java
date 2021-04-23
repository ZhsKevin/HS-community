package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.dto.LikeQueryDTO;
import xyz.hshuai.forum.model.Comment;
import xyz.hshuai.forum.model.Question;
import org.springframework.stereotype.Component;

@Component
public interface ThumbExtMapper {
    int incLikeCount(Comment comment);

    int incQuestionLikeCount(Question question);

    Integer count(LikeQueryDTO likeQueryDTO);
}
