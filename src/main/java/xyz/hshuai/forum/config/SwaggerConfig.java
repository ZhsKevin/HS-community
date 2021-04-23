package xyz.hshuai.forum.config;

/**
 * @author Hshuai
 * @version 1.0
 * @date 2020/12/5 23:21
 * @site hschool.hshuai.xyz
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
             //   .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("xyz.hshuai.forum.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("HSchool API Doc")
                .description("This is a restful api document of Hschool.")
                .contact(new Contact("Hshuai","hschool.hshuai.xyz","zhss1004@163.com"))
                .version("1.0")
                .build();
    }

}
