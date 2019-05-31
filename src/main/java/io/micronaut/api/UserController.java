package io.micronaut.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.micronaut.application.data.UserWithToken;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.micronaut.application.UserQueryService;
import io.micronaut.application.data.UserData;
import io.micronaut.core.user.EncryptService;
import io.micronaut.core.user.User;
import io.micronaut.core.user.UserRepository;

@Validated
@Controller("/users")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

	private UserRepository userRepository;
	private UserQueryService userQueryService;
    private String defaultImage;
    private EncryptService encryptService;
    //private JwtService jwtService;
    
    public UserController(UserRepository userRepository, UserQueryService userQueryServic,
    		EncryptService encryptService) {
    	this.encryptService = encryptService;
    	this.userQueryService = userQueryServic;
    	this.userRepository = userRepository;
    }
    
    @Post("/")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public UserData save(@Nullable Principal principal, 
    		@Body @Valid UserSaveCommand cmd) {
    	
    	User user = new User(cmd.getEmail(), cmd.getUsername(),  
    			encryptService.encrypt(cmd.getPassword()),
    			"", defaultImage);
    	
    	userRepository.save(user);
    	UserData userData = userQueryService.findById(user.getId()).get();
    	
    	return userData;
    	
    }
    
    protected URI location(String id) {
        return URI.create("/users/" + id);
    }

    protected URI location(User genre) {
        return location(genre.getId());
    }
    
    private Map<String, Object> userResponse(UserWithToken userWithToken) {
        return new HashMap<String, Object>() {{
            put("user", userWithToken);
        }};
    }
	
}


@Getter
@NoArgsConstructor
class UserSaveCommand {
	@NotBlank(message = "can't be empty")
	@Email(message = "should be an email")
    private String email;
	
	@NotBlank(message = "can't be empty")
    private String username;
	
	@NotBlank(message = "can't be empty")
    private String password;
	
    private String bio;
	
    private String image;

    
}
