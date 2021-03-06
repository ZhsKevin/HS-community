package xyz.hshuai.forum.controller;

import xyz.hshuai.forum.annotation.UserLoginToken;
import xyz.hshuai.forum.cache.TagCache;
import xyz.hshuai.forum.dto.QuestionDTO;
import xyz.hshuai.forum.dto.ResultDTO;
import xyz.hshuai.forum.dto.UserDTO;
import xyz.hshuai.forum.exception.CustomizeErrorCode;
import xyz.hshuai.forum.exception.CustomizeException;
import xyz.hshuai.forum.model.Question;
import xyz.hshuai.forum.provider.BaiduCloudProvider;
import xyz.hshuai.forum.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hshuai
 * @version 2.0
 * @date 2020/12/1 15:17
 * @site hschool.hshuai.xyz
 */

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private BaiduCloudProvider baiduCloudProvider;

    @UserLoginToken
    @GetMapping("p/publish")
    public String publish2(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "p/add";
    }

    @UserLoginToken
    @PostMapping("p/publish")
    public String doPublish2(
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("tag") String tag,
            @RequestParam("column2") Integer column2,
            @RequestParam("permission") Integer permission,
            @RequestParam("id") Long id,
            HttpServletRequest request,
            Model model
    ){

        String defaultDescription = "<p id=\"descriptionP\"></p>";
        description = description.replaceAll("<p id=\"descriptionP\"></p>", ""); //?????????????????????????????????p??????
        title = title.trim();
        tag = tag.trim();
        model.addAttribute("title",title);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());
        model.addAttribute("column2", column2);
        model.addAttribute("id", id);
        model.addAttribute("navtype", "publishnav");
        model.addAttribute("permission", permission);
        UserDTO user = (UserDTO)request.getAttribute("loginUser");
        //UserAccount userAccount = (UserAccount)request.getSession().getAttribute("userAccount");

      /*  if(user==null) {
            model.addAttribute("error","???????????????");
            model.addAttribute("description", description);
            return "p/add";
        }*/

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "??????????????????");
            return "p/add";
        }
        if (description == null || defaultDescription.equals(description)) {
            model.addAttribute("error", "????????????????????????");
            return "p/add";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "??????????????????");
            return "p/add";
        }
        //??????
        ResultDTO resultDTO = baiduCloudProvider.getTextCensorReult(questionService.getTextDescriptionFromHtml(title+description+tag));
        if(resultDTO.getCode()!=1){
            model.addAttribute("error",resultDTO.getMessage());
            model.addAttribute("description", description);
            return "p/add";
        }
//????????????????????????????????????????????????????????????????????????????????????????????????????????????
      /*  String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "??????????????????:" + invalid);
            return "p/publish";
        }*/

        Question question = new Question();
        question.setPermission(permission);
        question.setColumn2(column2);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        //question.setGmtCreate(System.currentTimeMillis());
        //question.setGmtModified(question.getGmtCreate());
        // questionMapper.creat(question);
        questionService.createOrUpdate(question,user);
        return "redirect:/forum";
    }

    @GetMapping("p/publish/{id}")
    public String edit2(@PathVariable(name = "id") Long id,
                       Model model,
                       HttpServletRequest request){
        QuestionDTO question = questionService.getById(id,0L);
        UserDTO user = (UserDTO)request.getAttribute("loginUser");
        if (question.getCreator().longValue() != user.getId().longValue() ){
            throw new CustomizeException(CustomizeErrorCode.CAN_NOT_EDIT_QUESTION);
        }
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("column2", question.getColumn2());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        model.addAttribute("navtype", "publishnav");
        return "p/add";
    }





}
