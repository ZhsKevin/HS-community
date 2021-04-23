package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.dto.UserQueryDTO;
import xyz.hshuai.forum.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserExtMapper {
    List<User> selectLatestLoginUser(UserQueryDTO userQueryDTO);
    Integer count(UserQueryDTO userQueryDTO);
}
