package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.dto.QuestionQueryDTO;
import xyz.hshuai.forum.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    List<Question> selectTop(QuestionQueryDTO questionQueryDTO);


    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
