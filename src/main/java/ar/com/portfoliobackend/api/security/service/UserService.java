package ar.com.portfoliobackend.api.security.service;

import ar.com.portfoliobackend.api.security.model.User;
import ar.com.portfoliobackend.api.security.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    public Optional<User> getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
    
    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
    
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    
    public void save(User user){
        userRepository.save(user);
    }
}
