package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.model.Person;
import ar.com.portfoliobackend.api.service.PersonService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PutMapping
    public void updatePerson(@RequestBody Person person){
        personService.updatePerson(person);
    }
    
}
