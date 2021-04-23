package xyz.hshuai.forum.mapper;

import xyz.hshuai.forum.model.UserAccount;
import xyz.hshuai.forum.model.UserAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Component
public interface UserAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    long countByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int deleteByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    List<UserAccount> selectByExampleWithRowbounds(UserAccountExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    List<UserAccount> selectByExample(UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    UserAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByExample(@Param("record") UserAccount record, @Param("example") UserAccountExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByPrimaryKey(UserAccount record);
}