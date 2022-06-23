package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.exception.ResourceNotFoundException;
import ar.com.portfoliobackend.api.model.Education;
import ar.com.portfoliobackend.api.model.Person;
import ar.com.portfoliobackend.api.service.EducationService;
import ar.com.portfoliobackend.api.service.PersonService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class EducationController {
    
    @Autowired
    PersonService personService;
    
    @Autowired
    EducationService educationService;
    
    @GetMapping("/{personId}/educations")
    public ResponseEntity<List> getAllEducationsByPersonId(@PathVariable(value = "personId") Long personId){
        List<Education> educations = educationService.findByPersonId(personId);
        return new ResponseEntity<List>(educations, HttpStatus.OK);
    }
    
    @GetMapping("/educations/{educationId}")
    public Optional<Education> getEducationById(@PathVariable(value = "educationId") Long educationId){
        Optional<Education> education = educationService.findById(educationId);
        return education;
    }
            
    @PostMapping("/{personId}/educations")
    public ResponseEntity<Education> createEducation(@PathVariable(value = "personId") Long personId, 
                                                     @RequestBody Education educationRequest) throws ResourceNotFoundException{
        Education education = personService.getPersonById(personId).map(person -> {
            educationRequest.setPerson(person);
            return educationService.saveEducation(educationRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la persona " + personId)); 
        return new ResponseEntity<Education>(education, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/educations/{educationId}")
    public ResponseEntity<HttpStatus> deleteEducation(@PathVariable("educationId") Long educationId){
        educationService.deleteEducationById(educationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}
