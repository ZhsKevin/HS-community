package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.dto.TalkQueryDTO;
import xyz.hshuai.forum.model.Talk;
import org.springframework.stereotype.Component;

/**
 * @author Hshuai
 * @version 1.0
 * @date 2020/12/24 21:53
 * @site hschool.hshuai.xyz
 */
@Component
public interface TalkExtMapper {

    Integer count(TalkQueryDTO talkQueryDTO);

    int updateByPrimaryKeySelective(Talk talk);
}
