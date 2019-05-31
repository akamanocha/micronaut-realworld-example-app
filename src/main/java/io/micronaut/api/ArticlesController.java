package io.micronaut.api;

import io.micronaut.application.UserQueryService;
import io.micronaut.application.data.UserData;
import io.micronaut.core.user.EncryptService;
import io.micronaut.core.user.UserRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.validation.Validated;

@Validated
@Controller("/articles")
@Secured("isAuthenticated()")
public class ArticlesController {
	
	private UserQueryService userQueryService;
	
	public ArticlesController(UserQueryService userQueryService) {
    	this.userQueryService = userQueryService;
    }

	/**
	 * TODO: a. Change it to real article
	 * TODO: b. test JWT authentication for now, Tested? 
	 * TODO: c. How to save that in database + create table
	 * 
	 * @author abhishek.manocha
	 * @return articles list, 
	 */
	@Get("/")
	public String articles() {
		return "{}";
	}
	
	/**
	 * It's a dummy api.
	 * 
	 * @author abhishek.manocha
	 * @return Return the User koolak82 profile, it's all hardcoded.
	 */
	
	@Get("/dummy")
	public UserData dummy() {
		return userQueryService.findByUsername("koolak82").get();
	}
}
