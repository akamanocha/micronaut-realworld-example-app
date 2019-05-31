package io.micronaut.api;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;

import java.security.Principal;

import io.micronaut.http.HttpStatus;

@Controller("/")
@Secured("isAuthenticated()")
public class HelloController {

    @Get("/")
    public String index(Principal principal) {
        return principal.getName();
    }
}