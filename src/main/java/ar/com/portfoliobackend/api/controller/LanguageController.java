package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.exception.ResourceNotFoundException;
import ar.com.portfoliobackend.api.model.Language;
import ar.com.portfoliobackend.api.service.LanguageService;
import ar.com.portfoliobackend.api.service.PersonService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class LanguageController {
    @Autowired
    PersonService personService;
    
    @Autowired
    LanguageService languageService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personId}/languages")
    public ResponseEntity<Language> createLanguage(@PathVariable(value = "personId") Long personId, 
                                                   @Valid                                         
                                                   @RequestBody Language languageRequest) 
                                                   throws ResourceNotFoundException{
        Language language = personService.getPersonById(personId).map(person -> {
            languageRequest.setPerson(person);
            return languageService.saveLanguage(languageRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la persona " + personId)); 
        return new ResponseEntity<Language>(language, HttpStatus.CREATED);
    }
    
    @GetMapping("/{personId}/languages")
    public ResponseEntity<List> getAllLanguagesByPersonId(@PathVariable(value = "personId") Long personId){
        List<Language> languages = languageService.findByPersonId(personId);
        return new ResponseEntity<List>(languages, HttpStatus.OK);
    }
    
    @GetMapping("/languages/{languageId}")
    public Optional<Language> getLanguageById(@PathVariable(value = "languageId") Long languageId){
        Optional<Language> language = languageService.findById(languageId);
        return language;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/languages/{languageId}")
    public ResponseEntity<Language> updateLanguage(@PathVariable("languageId") Long languageId, 
                                                   @Valid
                                                   @RequestBody Language language)
                                                   throws ResourceNotFoundException{
            languageService.findById(languageId).map(languageData -> {
            Language _language = new Language();
            _language.setId(languageId);
            _language.setLanguage(language.getLanguage());
            _language.setScore(language.getScore());
            _language.setPerson(languageData.getPerson());
            return languageService.saveLanguage(_language);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el lenguaje " + languageId));
        return new ResponseEntity<Language>(language, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/languages/{languageId}")
    public ResponseEntity<HttpStatus> deleteLanguage(@PathVariable("languageId") Long languageId){
        languageService.deleteLanguageById(languageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
