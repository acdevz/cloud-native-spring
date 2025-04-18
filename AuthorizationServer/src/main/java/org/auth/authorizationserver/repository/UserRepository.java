package org.auth.authorizationserver.repository;

import org.auth.authorizationserver.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<User, Long> {
    UserDetails findByUsername(String username);
}
