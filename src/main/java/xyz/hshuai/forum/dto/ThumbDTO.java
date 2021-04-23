package xyz.hshuai.forum.dto;

import lombok.Data;

/**
 * @author Hshuai
 * @version 1.0
 * @date 2020/12/13 23:24
 * @site hschool.hshuai.xyz
 */
@Data
public class ThumbDTO {
    private Long id;
    private Long targetId;
    private Integer type;
    private UserDTO user;
    private Long gmtCreate;
    private Long gmtModified;

}
