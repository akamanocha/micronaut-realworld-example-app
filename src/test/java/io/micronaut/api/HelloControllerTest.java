package io.micronaut.api;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.annotation.MicronautTest;

import org.junit.jupiter.api.Test;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;

@MicronautTest
public class HelloControllerTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Test
    public void testIndex() throws Exception {
        try(RxHttpClient client = embeddedServer.getApplicationContext().
        		createBean(RxHttpClient.class, embeddedServer.getURL())) {
        	
        	assertThrows(HttpClientResponseException.class, () -> { client.toBlocking().exchange("/"); });
            //assertEquals(HttpStatus.UNAUTHORIZED, client.toBlocking().exchange("/").status());
        }
    }
    
    
    /*
    @Test
    public void testSuccessfulLogin() throws ParseException {
    	try (RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {
    		
    		UsernamePasswordCredentials creds = new UsernamePasswordCredentials("sherlock", "password");
    		HttpRequest request = HttpRequest.POST("/login", creds);
    		HttpResponse<BearerAccessRefreshToken> rsp = client.toBlocking().exchange(request, BearerAccessRefreshToken.class);
    		
    		assertEquals(HttpStatus.OK, rsp.status());
    		assertEquals("sherlock", rsp.body().getUsername());
    		
    		assertTrue(JWTParser.parse(rsp.body().getAccessToken()) instanceof SignedJWT);
    		assertTrue(JWTParser.parse(rsp.body().getRefreshToken()) instanceof SignedJWT);
    	}
    }
    */
    
    
    
}
