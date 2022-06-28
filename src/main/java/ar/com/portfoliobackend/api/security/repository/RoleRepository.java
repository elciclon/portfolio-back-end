package ar.com.portfoliobackend.api.security.repository;

import ar.com.portfoliobackend.api.security.enums.RoleName;
import ar.com.portfoliobackend.api.security.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRoleName(RoleName roleName);
}
