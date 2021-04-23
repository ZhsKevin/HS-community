package xyz.hshuai.forum.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

   // static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };

    /**

     - /**： 匹配所有路径
     - /admin/**：匹配 /admin/ 下的所有路径
     - /secure/*：只匹配 /secure/user，不匹配 /secure/user/info

     */


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
        //也可排除某些不需要拦截的路径

        //另一个拦截器
        //registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/**");
        //注意这个拦截器什么都会拦截，包括js和其他配置文件
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

  /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods(ORIGINS)
                .allowedHeaders("*")
              //  .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                //.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .maxAge(3600);
    }*/

/*
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("10240KB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }*/

}
