package io.micronaut.core.user;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import io.micronaut.core.user.FollowRelation;
import io.micronaut.core.user.User;

public interface UserRepository {
	
	User save(User user);
	
	//TODO: Remove this, used above finally, mark deprecated
    User save(@NotBlank String email, @NotBlank String username, @NotBlank String password, 
			@NotBlank String bio, @NotBlank String image);

    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void saveRelation(FollowRelation followRelation);

    Optional<FollowRelation> findRelation(String userId, String targetId);

    //void removeRelation(FollowRelation followRelation);

}
