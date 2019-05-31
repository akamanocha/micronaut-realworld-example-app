package io.micronaut;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
            title = "micronaut-realworld-example-app",
            version = "0.1",
            description = "Example Micronaut backend app adhering to RealWorld API spec, "
            		+ "starting point has been same realworld app in Spring Boot.",
            license = @License(name = "MIT"),
            contact = @Contact(url = "https://twitter.com/koolak82", name = "Abhishek Manocha", email = "abhishek.manocha@gmail.com")
    )
)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}