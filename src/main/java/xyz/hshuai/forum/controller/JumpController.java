package xyz.hshuai.forum.controller;

import xyz.hshuai.forum.dto.PaginationDTO;
import xyz.hshuai.forum.dto.UserDTO;
import xyz.hshuai.forum.dto.UserQueryDTO;
import xyz.hshuai.forum.exception.CustomizeErrorCode;
import xyz.hshuai.forum.exception.CustomizeException;
import xyz.hshuai.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Hshuai
 * @version 1.0
 * @date 2020/12/6 20:20
 * @site hschool.hshuai.xyz
 */
@Controller
public class JumpController {
    @Autowired
    private UserService userService;

    @GetMapping("/jump")
    public String jump(
            HttpServletRequest request,
            Model model
            ,@RequestParam(name = "type") String type
            ,@RequestParam(name = "target") String target){

        if("user_home".equals(type)){
            UserQueryDTO userQueryDTO = new UserQueryDTO();
            userQueryDTO.setName(target);
            userQueryDTO.convert();
            PaginationDTO paginationDTO = userService.list(userQueryDTO);
            List<UserDTO> userDTOs = paginationDTO.getData();
            if(userDTOs.size()!=1)
                throw new CustomizeException(CustomizeErrorCode.USER_IS_EMPTY);
            return "redirect:/user/"+userDTOs.get(0).getId();
        }



        return "redirect:/forum";
    }



}
