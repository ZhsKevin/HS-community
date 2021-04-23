package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public interface UserAccountExtMapper {

    int incScore(UserAccount userAccount);
    int decScore(UserAccount userAccount);
}
