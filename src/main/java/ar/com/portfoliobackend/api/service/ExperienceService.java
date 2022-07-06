package ar.com.portfoliobackend.api.service;

import ar.com.portfoliobackend.api.model.Experience;
import ar.com.portfoliobackend.api.repository.IExperienceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExperienceService {
    @Autowired
    IExperienceRepository experienceRepository;
    public Experience findById;
    public List<Experience> findByPersonId(Long personId) {
        return experienceRepository.findByPersonId(personId);
    }
    
    public Experience saveExperience(Experience experience){
        return experienceRepository.save(experience);
    }

    public Optional<Experience> findById(Long experienceId) {
        return experienceRepository.findById(experienceId);
    }

    public void deleteExperienceById(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }
    
}
