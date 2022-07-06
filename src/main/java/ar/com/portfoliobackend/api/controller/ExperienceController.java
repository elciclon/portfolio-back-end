package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.exception.ResourceNotFoundException;
import ar.com.portfoliobackend.api.model.Experience;
import ar.com.portfoliobackend.api.service.ExperienceService;
import ar.com.portfoliobackend.api.service.PersonService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/person")
public class ExperienceController {
    @Autowired
    PersonService personService;
    
    @Autowired
    ExperienceService experienceService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personId}/experiences")
    public ResponseEntity<Experience> createExperience(@PathVariable(value = "personId") Long personId, 
                                                     @Valid                                         
                                                     @RequestBody Experience experienceRequest) 
                                                     throws ResourceNotFoundException{
        Experience experience = personService.getPersonById(personId).map(person -> {
            experienceRequest.setPerson(person);
            return experienceService.saveExperience(experienceRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la persona " + personId)); 
        return new ResponseEntity<Experience>(experience, HttpStatus.CREATED);
    }
    
    @GetMapping("/{personId}/experiences")
    public ResponseEntity<List> getAllExperiencesByPersonId(@PathVariable(value = "personId") Long personId){
        List<Experience> experiences = experienceService.findByPersonId(personId);
        return new ResponseEntity<List>(experiences, HttpStatus.OK);
    }
    
    @GetMapping("/experiences/{experienceId}")
    public Optional<Experience> getExperienceById(@PathVariable(value = "experienceId") Long experienceId){
        Optional<Experience> experience = experienceService.findById(experienceId);
        return experience;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/experiences/{experienceId}")
    public ResponseEntity<Experience> updateExperience(@PathVariable("experienceId") Long experienceId, 
                                                       @Valid
                                                       @RequestBody Experience experience)
                                                       throws ResourceNotFoundException{
            experienceService.findById(experienceId).map(experienceData -> {
            Experience _experience = new Experience();
            _experience.setId(experienceId);
            _experience.setFirm(experience.getFirm());
            _experience.setJob(experience.getJob());
            _experience.setStart(experience.getStart());
            _experience.setEnd(experience.getEnd());
            _experience.setUrlImage(experience.getUrlImage());
            _experience.setPerson(experienceData.getPerson());
            return experienceService.saveExperience(_experience);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la experiencia " + experienceId));
        return new ResponseEntity<Experience>(experience, HttpStatus.CREATED);
    }
    
}
