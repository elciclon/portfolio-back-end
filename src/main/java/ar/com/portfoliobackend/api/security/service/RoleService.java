package ar.com.portfoliobackend.api.security.service;

import ar.com.portfoliobackend.api.security.enums.RoleName;
import ar.com.portfoliobackend.api.security.model.Role;
import ar.com.portfoliobackend.api.security.repository.RoleRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    
    @Autowired
    RoleRepository roleRepository;
    
    public Optional<Role> findByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName); 
    }
    
    public void save(Role role){
        roleRepository.save(role);
    }
}
