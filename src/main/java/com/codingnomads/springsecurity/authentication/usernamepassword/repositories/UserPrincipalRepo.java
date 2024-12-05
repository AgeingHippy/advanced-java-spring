/* CodingNomads (C)2024 */
package com.codingnomads.springsecurity.authentication.usernamepassword.repositories;

import com.codingnomads.springsecurity.authentication.usernamepassword.models.UserPrincipal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrincipalRepo extends JpaRepository<UserPrincipal, Long> {
    Optional<UserPrincipal> findByUsername(String username);
}
