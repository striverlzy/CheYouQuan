package com.cyq.question.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author：liuzhongyu
 * @Date: 2019/12/31 15:44
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cyq.question"))
                .paths(PathSelectors.any()).build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("问答模块 APIs")
                .description("问答模块")
                .termsOfServiceUrl("https://blog.csdn.net/qq_41937388")
                .version("1.0").build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(null, "list", "alpha", "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
    }
}

