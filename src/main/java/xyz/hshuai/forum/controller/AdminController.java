package xyz.hshuai.forum.controller;

import xyz.hshuai.forum.dto.PaginationDTO;
import xyz.hshuai.forum.dto.UserDTO;
import xyz.hshuai.forum.exception.CustomizeErrorCode;
import xyz.hshuai.forum.exception.CustomizeException;
import xyz.hshuai.forum.mapper.UserAccountMapper;
import xyz.hshuai.forum.model.UserAccount;
import xyz.hshuai.forum.model.UserAccountExample;
import xyz.hshuai.forum.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hshuai
 * @version 1.0
 * @date 2020/12/6 22:38
 * @site hschool.hshuai.xyz
 */
@Controller
public class AdminController {


    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @GetMapping("/admin2508")
    public String index(HttpServletRequest request,
                       Model model){
        UserDTO user = (UserDTO)request.getAttribute("loginUser");

        if(user==null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }

        return "admin/index";
    }

    @GetMapping("/admin2538/p/{action}")
    public String p(HttpServletRequest request,
                    @PathVariable(name = "action") String action,
                    Model model,
                    @RequestParam(name = "page",defaultValue = "1")Integer page,
                    @RequestParam(name = "size",defaultValue = "10")Integer size){
        UserDTO user = (UserDTO)request.getAttribute("loginUser");

        if(user==null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        UserAccount userAccount =null;
        if(user!=null){
            userAccount = new UserAccount();
            BeanUtils.copyProperties(user,userAccount);
            userAccount.setUserId(user.getId());
        }
        if("list".equals(action)){
            model.addAttribute("section", "list");
            model.addAttribute("sectionName", "帖子列表");
            PaginationDTO pagination = questionService.listwithColumn(null, null, null, page,size,null,userAccount);

           // PaginationDTO pagination = questionService.listByUserId(user.getId(), page, size);
            model.addAttribute("pagination",pagination);
            model.addAttribute("navtype", "communitynav");
        }
        if("likes".equals(action)){
            PaginationDTO paginationDTO = questionService.listByExample(user.getId(), page, size,"likes");
            model.addAttribute("section", "likes");
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("sectionName", "我的收藏");
            model.addAttribute("navtype", "communitynav");
        }

        return "admin/p";
    }

    @GetMapping("/admin2568/user/{action}")
    public String user(HttpServletRequest request,
                    @PathVariable(name = "action") String action,
                    Model model,
                    @RequestParam(name = "page",defaultValue = "1")Integer page,
                    @RequestParam(name = "size",defaultValue = "10")Integer size){
        UserDTO user = (UserDTO)request.getAttribute("loginUser");

        if(user==null){
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }

        if("set".equals(action)){
            model.addAttribute("section", "account");
            model.addAttribute("sectionName", "用户设置");
            model.addAttribute("navtype", "communitynav");
        }
        if("likes".equals(action)){
            PaginationDTO paginationDTO = questionService.listByExample(user.getId(), page, size,"likes");
            model.addAttribute("section", "likes");
            model.addAttribute("pagination", paginationDTO);
            model.addAttribute("sectionName", "我的收藏");
            model.addAttribute("navtype", "communitynav");
        }

        return "admin/user";
    }

    @PostMapping("/user2588/setAdmin/id")
    @ResponseBody
    public Map<String,Object> setQuestionById(HttpServletRequest request,
                                              @RequestParam(name = "id",defaultValue = "0") Long id) {

        UserDTO user = (UserDTO)request.getAttribute("loginUser");
        //UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        if (user == null) {
            throw new CustomizeException(CustomizeErrorCode.NO_LOGIN);
        }
        Map<String,Object> map  = new HashMap<>();
        UserAccount userAccount = new UserAccount();
        userAccount.setGroupId(19);
        UserAccountExample userAccountExample = new UserAccountExample();
        userAccountExample.createCriteria().andUserIdEqualTo(id);
        if(userAccountMapper.updateByExampleSelective(userAccount,userAccountExample)==1){
            map.put("code",200);
            map.put("message","恭喜您，设置成功！");
        }
        return map;

    }
}
