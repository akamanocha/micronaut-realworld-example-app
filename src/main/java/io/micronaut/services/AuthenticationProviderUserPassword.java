package io.micronaut.services;

import io.micronaut.core.user.User;
import io.micronaut.core.user.UserRepository;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;

import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Optional;

@Singleton 
@RequiredArgsConstructor
public class AuthenticationProviderUserPassword implements AuthenticationProvider  { 
	
	private String header = "Authorization";
	
	private UserRepository userRepository;
	
    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
    	
    	Optional<User> agent = userRepository.findByUsername((String) authenticationRequest.getIdentity());

        if (agent.isPresent() && agent.get().passwordMatches((String) authenticationRequest.getSecret())) {
            return Flowable.just(createUserDetails(agent.get()));
        }

        return Flowable.just(new AuthenticationFailed());
    	
    	/*
        if ( authenticationRequest.getIdentity().equals("sherlock") &&
                authenticationRequest.getSecret().equals("password") ) {
            return Flowable.just(new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>()));
        }
        return Flowable.just(new AuthenticationFailed());
        */
    }
    
    private UserDetails createUserDetails(User user) {
    	return new UserDetails(user.getUsername(), new ArrayList<>());
    }
}
