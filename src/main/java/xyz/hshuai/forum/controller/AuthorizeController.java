package xyz.hshuai.forum.controller;

import xyz.hshuai.forum.dto.*;
import xyz.hshuai.forum.dto.*;
import xyz.hshuai.forum.model.User;
import xyz.hshuai.forum.model.UserExample;
import xyz.hshuai.forum.model.UserInfo;
import xyz.hshuai.forum.provider.BaiduProvider;
import xyz.hshuai.forum.provider.GithubProvider;
import xyz.hshuai.forum.provider.QqProvider;
import xyz.hshuai.forum.provider.WeiboProvider;
import xyz.hshuai.forum.service.UserAccountService;
import xyz.hshuai.forum.service.UserService;
import xyz.hshuai.forum.util.CookieUtils;
import xyz.hshuai.forum.util.TokenUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Hshuai
 * @version 2.0
 * @date 2020/12/1 15:17
 * @site hschool.hshuai.xyz
 */

@Slf4j
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private BaiduProvider baiduProvider;

    @Autowired
    private WeiboProvider weiboProvider;

    @Autowired
    private QqProvider qqProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Value("${baidu.client.id}")
    private String baiduClientId;

    @Value("${baidu.client.secret}")
    private String baiduClientSecret;

    @Value("${baidu.redirect.uri}")
    private String baiduRedirectUri;

    @Value("${weibo.client.id}")
    private String weiboClientId;

    @Value("${weibo.client.secret}")
    private String weiboClientSecret;

    @Value("${weibo.redirect.uri}")
    private String weiboRedirectUri;

    @Value("${qq.client.id}")
    private String qqClientId;

    @Value("${qq.client.secret}")
    private String qqClientSecret;

    @Value("${qq.redirect.uri}")
    private String qqRedirectUri;
    @Value("${qq.api.unionId}")
    private int getQqUnionId;
    @Value("${site.main.domain}")
    private String domain;

    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CookieUtils cookieUtils;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //System.out.println(githubUser.getName()+githubUser.getId());
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            /*if(githubUser.getName()==null)
                user.setName(getUserName("Github"));
            else */
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
//            user.setGmtCreate(System.currentTimeMillis());
//            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl("/images/avatar/" + (int) (Math.random() * 11) + ".jpg");
            user = userService.createOrUpdate(user);
            UserDTO userDTO = userService.getUserDTO(user);
            Cookie cookie = cookieUtils.getCookie("token",tokenUtils.getToken(userDTO),86400*3);
            response.addCookie(cookie);
            return "redirect:/forum";
        } else {
            // ???????????????????????????
            log.error("callback get github error,{}", githubUser);
            return "redirect:/forum";
        }
        //return "index";
    }

    @GetMapping("/callbackbaidu")
    public String callbackBaidu(@RequestParam(name = "code") String code,
                                @RequestParam(name = "state") String state,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                Model model) {

        BaiduAccessTokenDTO baiduAccessTokenDTO = new BaiduAccessTokenDTO();
        baiduAccessTokenDTO.setGrant_type("authorization_code");
        baiduAccessTokenDTO.setCode(code);
        baiduAccessTokenDTO.setClient_id(baiduClientId);
        baiduAccessTokenDTO.setClient_secret(baiduClientSecret);
        baiduAccessTokenDTO.setRedirect_uri(baiduRedirectUri);
        // System.out.println("code???"+code+"state???"+state);
        String accessToken = baiduProvider.getAccessToken(baiduAccessTokenDTO);
        BaiduUserDTO baiduUser = baiduProvider.getUser(accessToken);
        // System.out.println(baiduUser.getUsername()+baiduUser.getOpenid());
        if (baiduUser != null && baiduUser.getOpenid() != null) {
            User user = new User();
            UserInfo userInfo = new UserInfo();
            String token = UUID.randomUUID().toString();
            user.setName(baiduUser.getUsername());
            user.setToken(token);
            user.setBaiduAccountId("" + baiduUser.getOpenid());
            user.setAvatarUrl("https://gss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/" + baiduUser.getPortrait());
            BeanUtils.copyProperties(baiduUser, userInfo);
            UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
            int flag = userService.createOrUpdateBaidu(user, loginuser, userInfo);
           /* if (flag == 1) {//??????????????????
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "?????????????????????????????????????????????");
            }*/
            if (flag == 2) {
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "???????????????????????????/?????????????????????");
                return "redirect:/user/set/account#bind";
            }
           /* if (flag == 3) {
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "?????????????????????????????????????????????");
            }*/
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andBaiduAccountIdEqualTo(baiduUser.getOpenid());
            User dbUser = userService.selectUserByExample(userExample);
            if(dbUser==null) return "redirect:/forum";
            UserDTO userDTO = userService.getUserDTO(dbUser);
            Cookie cookie = cookieUtils.getCookie("token",tokenUtils.getToken(userDTO),86400*3);
            response.addCookie(cookie);
            return "redirect:/forum";

        } else {
            // ???????????????????????????
            log.error("callback get github error,{}", baiduUser);
            return "redirect:/forum";
        }
    }

    @GetMapping("/callbackqq")
    public String callbackQq(@RequestParam(name = "code") String code,
                             @RequestParam(name = "state") String state,
                             HttpServletResponse response,
                             HttpServletRequest request,
                             Model model) {

        QqAccessTokenDTO qqAccessTokenDTO = new QqAccessTokenDTO();
        qqAccessTokenDTO.setGrant_type("authorization_code");
        qqAccessTokenDTO.setCode(code);
        qqAccessTokenDTO.setClient_id(qqClientId);
        qqAccessTokenDTO.setClient_secret(qqClientSecret);
        qqAccessTokenDTO.setRedirect_uri(qqRedirectUri);
        // System.out.println("code???"+code+"state???"+state);
        String string = qqProvider.getAccessToken(qqAccessTokenDTO);
        String access_token = string.split("&")[0].split("=")[1];
        String openid = qqProvider.getOpenID(access_token);
        QqUserDTO qqUser = qqProvider.getUser(access_token, qqClientId, openid);
        if (qqUser != null && qqUser.getOpenId() != null) {
            User user = new User();
            UserInfo userInfo = new UserInfo();
            if (getQqUnionId == 1) {
                user.setQqAccountId(qqProvider.getUnionId(access_token));
            } else {
                user.setQqAccountId(openid);
            }
            String token = UUID.randomUUID().toString();
            user.setName(qqUser.getNickname());
            user.setToken(token);
            //user.setQqAccountId(qqUser.getOpenId());
            user.setAvatarUrl(qqUser.getFigureurl_qq());
            userInfo.setSex(qqUser.getGender());
            if (StringUtils.isBlank(qqUser.getProvince())) qqUser.setProvince("??????");
            if (StringUtils.isBlank(qqUser.getCity())) qqUser.setCity("??????");
            userInfo.setLocation(qqUser.getProvince() + "-" + qqUser.getCity() + "-??????");
            if (!StringUtils.isBlank(qqUser.getConstellation())) userInfo.setConstellation(qqUser.getConstellation());
            userInfo.setBirthday(qqUser.getYear() + "-10-10");
            UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
            int flag = userService.createOrUpdateQq(user, loginuser, userInfo);
        /*    if (flag == 1) {//??????qq??????
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "????????????QQ???????????????????????????");
            }*/
            if (flag == 2) {
               /* model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "???????????????????????????/??????QQ?????????");*/
                return "redirect:/user/set/account#bind";
            }
         /*   if (flag == 3) {
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "????????????QQ???????????????????????????");
            }*/
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andQqAccountIdEqualTo(user.getQqAccountId());
            User dbUser = userService.selectUserByExample(userExample);
            if(dbUser==null) return "redirect:/forum";
            UserDTO userDTO = userService.getUserDTO(dbUser);
            Cookie cookie = cookieUtils.getCookie("token",tokenUtils.getToken(userDTO),86400*3);
            response.addCookie(cookie);
            return "redirect:/forum";

        } else {
            log.error("callback get qq error,{}", qqUser);
            return "redirect:/";
        }
    }

    @GetMapping("/callbackweibo")
    public String callbackWeibo(@RequestParam(name = "code") String code,
                                @RequestParam(name = "state") String state,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                Model model) {

        WeiboAccessTokenDTO weiboAccessTokenDTO = new WeiboAccessTokenDTO();
        weiboAccessTokenDTO.setGrant_type("authorization_code");
        weiboAccessTokenDTO.setCode(code);
        weiboAccessTokenDTO.setClient_id(weiboClientId);
        weiboAccessTokenDTO.setClient_secret(weiboClientSecret);
        weiboAccessTokenDTO.setRedirect_uri(weiboRedirectUri);
        // System.out.println("code???"+code+"state???"+state);
        String jsonString = weiboProvider.getAccessToken(weiboAccessTokenDTO);
        JSONObject obj = JSONObject.parseObject(jsonString);
        String access_token = obj.getString("access_token");
        String uid = obj.getString("uid");
        WeiboUserDTO weiboUser = weiboProvider.getUser(access_token, uid);
        if (weiboUser != null && weiboUser.getIdstr() != null) {
            User user = new User();
            UserInfo userInfo = new UserInfo();
            String token = UUID.randomUUID().toString();
            user.setName(weiboUser.getName());
            user.setToken(token);
            user.setWeiboAccountId(weiboUser.getIdstr());
            user.setAvatarUrl(weiboUser.getAvatar_hd());
            userInfo.setUserdetail(weiboUser.getDescription());
            if ("f".equals(weiboUser.getGender())) userInfo.setSex("???");
            else userInfo.setSex("???");
            userInfo.setLocation(weiboUser.getLocation());
            //BeanUtils.copyProperties(weiboUser,userInfo);
            UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
            int flag = userService.createOrUpdateWeibo(user, loginuser, userInfo);
          /*  if (flag == 1) {//??????????????????
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "?????????????????????????????????????????????");
            }*/
            if (flag == 2) {
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "???????????????????????????/?????????????????????");
                return "redirect:/user/set/account#bind";
            }
           /* if (flag == 3) {
                model.addAttribute("rsTitle", "??????????????????");
                model.addAttribute("rsMessage", "?????????????????????????????????????????????");

            }*/
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andWeiboAccountIdEqualTo(user.getWeiboAccountId());
            User dbUser = userService.selectUserByExample(userExample);
            if(dbUser==null) return "redirect:/forum";
            UserDTO userDTO = userService.getUserDTO(dbUser);
            Cookie cookie = cookieUtils.getCookie("token",tokenUtils.getToken(userDTO),86400*3);
            response.addCookie(cookie);
            return "redirect:/forum";

        } else {
            log.error("callback get github error,{}", weiboUser);
            return "redirect:/";
        }
    }


    @RequestMapping(value = "/creatAccount/baidu", method = RequestMethod.POST)
    public String creatAccountFromBaidu(HttpServletRequest request,
                                        HttpServletResponse response,
                                        @RequestParam(name = "name", required = false) String name,
                                        Model model) {
        UserInfo userInfoTemp = (UserInfo) request.getSession().getAttribute("userInfoTemp");
        User userTemp = (User) request.getSession().getAttribute("userTemp");
        if (name != null && StringUtils.isNotBlank(name)) userTemp.setName(name);
        userService.createNewBaidu(userTemp, userInfoTemp);
        return "result";
    }


    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

      /*  Cookie cookie = new Cookie("token", null);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(0);*/
        Cookie cookie = cookieUtils.getCookie("token",null,0);
        response.addCookie(cookie);
        return "redirect:/forum";
    }

    @PostMapping("/callbackOpenid")
    @ResponseBody
    public Map<String, Object> callbackOpenid(HttpServletRequest request,
                                              HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("info", "?????????");
        return map;
    }

    @Deprecated
    public String getUserName(String authorizeSize) {
        String str = RandomStringUtils.random(5,
                "abcdefghijklmnopqrstuvwxyz1234567890");
        String name = authorizeSize + "??????_" + str;
        return name;
    }


}
