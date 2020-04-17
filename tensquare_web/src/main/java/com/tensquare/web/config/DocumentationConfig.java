package com.tensquare.web.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/3 09:47
 * @Description:
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("用户模块", "/user/v2/api-docs", "2.0"));
        resources.add(swaggerResource("文章模块", "/article/v2/api-docs", "2.0"));
        resources.add(swaggerResource("活动模块", "/gathering/v2/api-docs", "2.0"));
        resources.add(swaggerResource("问答模块", "/question/v2/api-docs", "2.0"));
        resources.add(swaggerResource("车务模块", "/carwork/v2/api-docs", "2.0"));
        resources.add(swaggerResource("搜索模块", "/search/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
