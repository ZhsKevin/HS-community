# H&S社区

### 一个基于SpringBoot开发的轻量级社区社交项目

 H&S社区官方交流群：[484119131](https://qm.qq.com/cgi-bin/qm/qr?k=_eoN_mZop7orttneU9gBuUD1XQLFnL4A&jump_from=webapi "欢迎加入")



## 项目简介

​	H&S社区为一个社交论坛，用户注册后发布到帖子或者说说到网站上，实现匿名社交的功能。

​	项目采用前后端分离开发的方式，基于SpringBoot整合Mybatis，前端采用Thymeleaf结合LayUI，OAuth2与JWT鉴权，结合云计算技术搭建一个校园社交以及信息共享平台。为众多大学校园内的广大学生提供了一个社交平台,使众多大学生在此社交平台上实现信息共享，交流学习等功能。 

​	API文档规范：Swagger 2.0



## 在线体验

<img src="https://cdn.jsdelivr.net/gh/ZhsKevin/cdn/img/火箭.png" alt="avatar" style="zoom: 60%;" />社区网址：[https://hschool.hshuai.xyz ](https://hschool.hshuai.xyz/)  （手机电脑端通用）

<img src="https://cdn.jsdelivr.net/gh/ZhsKevin/cdn/img/星.png" alt="avatar" style="zoom: 60%;" />更新日志：[https://common.hshuai.xyz](https://common.hshuai.xyz/)（手机电脑端通用）



## 所用技术栈

1. SpringBoot框架。

2. Thymeleaf模板引擎。

3. 数据访问层：Mybatis，mybatis generator。

4. 数据库：MySql。

5. 服务器：内置Tomcat。

6. 前端相关:Jquery,Bootstrap，Ajax，Layer等。

7. 前端模板：LayUI_fly社区模板。

8. 文件上传：腾讯云COS对象存储。

9. 短信验证：极光短信。

10. 邮箱验证：腾讯企业邮箱。

11. 富文本编辑器：WangEditor。

12. OAuth2授权登入（QQ、微博、百度、Github）

13. 验证码：vaptcha

14. 扫码登录

15. 身份验证:JWT

    

## 主要功能：

1. 发帖
2. 编辑
3. 点赞
4. 收藏
5. 回复（支持楼中楼回复）
6. 视频帖[支持插入iframe代码和video视频链接，高度完美自适应](https://hschool.hshuai.xyz/p/492)
7. [阅读权限](https://hschool.hshuai.xyz/p/494)
8. 帖子分类
9. 话题标签
10. 图片处理（图片审核，图片水印，图片压缩，头像智能剪切）
11. 置顶帖
12. 精华帖
13. 内容审核
14. 分享
15. 管理面板

###### 用户相关

1. 登录（六大登录方式）
2. 注册（支持使用手机、邮箱、QQ、微博、百度、Github注册账号）
3. 账号体系(支持绑定账户)（手机号、邮箱号、QQ、微博、百度、Github六合一）
4. 上传头像[(支持人脸自动定位)](https://hschool.hshuai.xyz/p/497)
5. 积分策略
6. 用户组晋升
7. 会员特权
8. 消息通知
9. 个人主页
10. 更新资料
11. 设置、修改密码

###### 更多功能

1. 搜索

2. 排序

3. 说说板块[畅所欲言](https://hschool.hshuai.xyz/talk)

4. 看看板块[定期更新新闻资讯](https://hschool.hshuai.xyz/news)

5. 验证码-防灌水、攻击

6. 身份验证JWT

   

## 快速运行
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地  
3. 将[resources](/src/main/resources/ "resources")目录下的sql文件导入新创建的数据库。
4. 编辑[resources](/src/main/resources/ "resources")目录下的[application.properties](/src/main/resources/application.properties "application.properties")文件。
5. 编辑[resources](/src/main/resources/ "resources")目录下的[generatorConfig.xml](/src/main/resources/generatorConfig.xml "generatorConfig.xml")文件，配置数据库相关信息（只需修改数据库链接、用户名、密码）。
6. 运行打包命令
   ```sh 
   mvn clean package
   ```

7. 部署到服务器并运行项目  
   ```sh
    nohup java -jar NiterForum-2.5.jar >temp.txt &   
   ```
8. 访问项目
   ```
   https://yourdomain
   ```



## 项目演示

更多演示，请移步：[https://hschool.hshuai.xyz ](https://hschool.hshuai.xyz/)

## 目录结构
   ```
├─xyz.hshuai.forum         		--应用目录
   │  ├─advice             		--异常处理
   │  ├─annotation             	--自定义注解
   │  ├─api             		--功能接口
   │  ├─cache             		--缓存对象
   │  ├─config             		--配置类
   │  ├─constant             	--分页设置
   │  ├─controller         		--控制器目录
   │  ├─dto                		--数据传输层
   │  ├─enums              		--枚举类
   │  ├─exception          		--自定义异常
   │  ├─intercepter        		--拦截器
   │  ├─mapper		            --数据持久层
   │  ├─model              		--映射数据库实体类
   │  ├─provider           		--提供类
   │  ├─schedule		        --定时任务
   │  ├─service            		--业务逻辑层
   │  ├─utils              		--工具类
   │  ├─vo             			--视图对象
   │----CommunityApplication    --SpringBoot启动类
   ```


## 更多链接
### 联系我们

尼特社区官方交流群：[484119131](https://qm.qq.com/cgi-bin/qm/qr?k=_eoN_mZop7orttneU9gBuUD1XQLFnL4A&jump_from=webapi "欢迎加入")

官方交流社区：[https://hschool.hshuai.xyz](https://hschool.hshuai.xyz/ "欢迎交流")

更新日志：[https://common.hshuai.xyz](https://common.hshuai.xyz/ "欢迎订阅")

### 资料
[Spring 文档](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content/)
[es](https://elasticsearch.cn/explore)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap](https://v3.bootcss.com/getting-started/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
[菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
[Spring Dev Tool](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)
[Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)
[Markdown 插件](http://editor.md.ipandao.com/)
[UFfile SDK](https://github.com/ucloud/ufile-sdk-java)
[Count(*) VS Count(1)](https://mp.weixin.qq.com/s/Rwpke4BHu7Fz7KOpE2d3Lw)

### 工具
[Git](https://git-scm.com/download)
[Visual Paradigm](https://www.visual-paradigm.com)
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)
[Lombok](https://www.projectlombok.org)
[ctotree](https://www.octotree.io/)
[Table of content sidebar](https://chrome.google.com/webstore/detail/table-of-contents-sidebar/ohohkfheangmbedkgechjkmbepeikkej)
[One Tab](https://chrome.google.com/webstore/detail/chphlpgkkbolifaimnlloiipkdnihall)
[Live Reload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei/related)
[Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)

### 特别感谢
[码问](http://www.mawen.co/)
[LayUI](https://fly.layui.com/)


## 其它可能操作
```bash
mvn flyway:migrate

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
# HS-community
