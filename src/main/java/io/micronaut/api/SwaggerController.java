package io.micronaut.api;

import static java.util.Collections.singletonList;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.micronaut.SwaggerConfig;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.LoggerFactory;

@Hidden
@Controller("/api")
@Validated
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SwaggerController {

    @Inject
    SwaggerConfig config;

    @View("swagger/index")
    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    public SwaggerConfig index() {
        LoggerFactory.getLogger(SwaggerController.class).info("Trying to render swagger-view");
        return config;
    }

    @View("swagger/index")
    @Get("/{url}")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public SwaggerConfig renderSpec(@NotNull String url) {
        return new SwaggerConfig.Builder()
                .withDeepLinking(config.isDeepLinking())
                .withLayout(config.getLayout())
                .withVersion(config.getVersion())
                .withUrls(singletonList(new SwaggerConfig.URIConfig.Builder()
                        .withName(url)
                        .withURI(url)
                        .build()))
                .build();
    }

    @View("swagger/index")
    @Post
    @Secured(SecurityRule.IS_ANONYMOUS)
    public SwaggerConfig renderSpecs(@Body @NotEmpty List<SwaggerConfig.URIConfig> urls) {
        return new SwaggerConfig.Builder()
                .withDeepLinking(config.isDeepLinking())
                .withLayout(config.getLayout())
                .withVersion(config.getVersion())
                .withUrls(urls)
                .build();
    }

}
