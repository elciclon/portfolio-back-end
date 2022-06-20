package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.model.Person;
import ar.com.portfoliobackend.api.service.PersonService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    @PostMapping
    public void savePerson(@RequestBody Person person){
        personService.savePerson(person);
    }
    
    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person person){
        Optional<Person> personData = personService.getPersonById(id);
        if (personData.isPresent()){
            Person _person = personData.get();
            _person.setFullName(person.getFullName());
            _person.setDateOfBirth(person.getDateOfBirth());
            _person.setNationality(person.getNationality());
            _person.setEmail(person.getEmail());
            _person.setAboutMe(person.getAboutMe());
            _person.setJob(person.getJob());
            _person.setLocation(person.getLocation());
            _person.setBannerImage(person.getBannerImage());
            _person.setProfileImage(person.getProfileImage());
            personService.updatePerson(_person);
            
            return new ResponseEntity<Person>(_person, HttpStatus.CREATED);
            
        }
        else {
                return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
                }
        
        
    }
    
}
