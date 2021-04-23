package xyz.hshuai.forum.api;

import xyz.hshuai.forum.annotation.UserLoginToken;
import xyz.hshuai.forum.dto.ThumbDTO;
import xyz.hshuai.forum.dto.UserDTO;
import xyz.hshuai.forum.service.LikeService;
import xyz.hshuai.forum.vo.ThumbVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Hshuai
 * @version 1.0
 * @date 2020/12/13 23:07
 * @site hschool.hshuai.xyz
 */
@Controller
@RequestMapping("/api/like")
@Api(tags={"点赞/收藏接口"})
public class LikeApi {
    @Autowired
    private LikeService likeService;

    @UserLoginToken
    @ApiOperation(value = "取消收藏",notes = "只有当作者登录后才能取消收藏")
    @DeleteMapping("/remove")
    @ResponseBody
    public Object removeLikeById(HttpServletRequest request
            ,@RequestBody @Valid ThumbVO thumbVO
                                 ) {
        UserDTO userDTO = (UserDTO) request.getAttribute("loginUser");
        ThumbDTO thumbDTO = new ThumbDTO();
        BeanUtils.copyProperties(thumbVO, thumbDTO);
        thumbDTO.setUser(userDTO);
        return likeService.remove(thumbDTO);
        //return likeService.removeLikeByIdAndType(userDTO.getId(),thumbVO.getTargetId(),thumbVO.getType());
    }

    @UserLoginToken
    @ApiOperation(value = "新增收藏",notes = "只有当作者登录后才能收藏")
    @PostMapping("/insert")
    @ResponseBody
    public Object insert(HttpServletRequest request
           ,@RequestBody @Valid ThumbVO thumbVO) {

        UserDTO user = (UserDTO) request.getAttribute("loginUser");
        ThumbDTO thumbDTO = new ThumbDTO();
        BeanUtils.copyProperties(thumbVO, thumbDTO);
        thumbDTO.setGmtCreate(System.currentTimeMillis());
        thumbDTO.setGmtModified(thumbDTO.getGmtCreate());
        thumbDTO.setUser(user);
       return likeService.insert(thumbDTO);
    }


}



