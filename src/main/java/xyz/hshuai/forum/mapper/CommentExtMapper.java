package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.dto.CommentQueryDTO;
import xyz.hshuai.forum.model.Comment;
import org.springframework.stereotype.Component;

@Component
public interface CommentExtMapper {
    int incCommentCount(Comment comment);

    Integer countBySearch(CommentQueryDTO commentQueryDTO);

}
