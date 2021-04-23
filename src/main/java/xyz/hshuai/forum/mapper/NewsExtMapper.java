package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.dto.NewsQueryDTO;
import xyz.hshuai.forum.model.News;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface NewsExtMapper {
    int incView(News record);
    Integer countBySearch(NewsQueryDTO newsQueryDTO);
    List<News> selectBySearch(NewsQueryDTO newsQueryDTO);

}
