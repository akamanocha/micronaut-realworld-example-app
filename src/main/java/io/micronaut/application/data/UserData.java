package io.micronaut.application.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name="UserData", description="User Data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	
    private String id;
    
    private String email;
    private String username;
    private String bio;
    private String image;
    
    @Schema(name = "id", description="A system generated Id, you can't pprovide this")
    public String getId() {
    	return id;
    }
    
    @Schema(name = "email", description="A valid email address")
    public String getEmail() {
    	return email;
    }
    
    @Schema(name = "username", description="A unique username")
    public String getUsername() {
    	return username;
    }
    
    @Schema(name = "bio", description="A very good and concise bio")
    public String getBio() {
    	return bio;
    }
    
    @Schema(name = "image", description="How about an image")
    public String getImage() {
    	return image;
    }
}
