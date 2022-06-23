package ar.com.portfoliobackend.api.service;

import ar.com.portfoliobackend.api.model.Education;
import ar.com.portfoliobackend.api.repository.IEducationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    
    @Autowired
    IEducationRepository educationRepository;
    public Education findById;
    public List<Education> findByPersonId(Long personId) {
        return educationRepository.findByPersonId(personId);
    }
    
    public Education saveEducation(Education education){
        return educationRepository.save(education);
    }

    public Optional<Education> findById(Long educationId) {
        return educationRepository.findById(educationId);
    }

    public void deleteEducationById(Long educationId) {
        educationRepository.deleteById(educationId);
    }
    
}
