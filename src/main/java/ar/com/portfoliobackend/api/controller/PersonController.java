package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.dto.PersonDTO;
import ar.com.portfoliobackend.api.exception.ResourceNotFoundException;
import ar.com.portfoliobackend.api.model.Person;
import ar.com.portfoliobackend.api.service.PersonService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    
    @Autowired
    PersonService personService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void savePerson(@RequestBody Person person){
        personService.savePerson(person);
    }
    
    @GetMapping("/allpersons")
    @ResponseBody
    public List<Person> getPersons(){
        return personService.getPersons();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<PersonDTO> getPersonDtoById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        PersonDTO personDto = new PersonDTO();
        personService.getPersonById(id).map(person -> {
            personDto.setId(person.getId());
            personDto.setFullName(person.getFullName());
            personDto.setDateOfBirth(person.getDateOfBirth());
            personDto.setNationality(person.getNationality());
            personDto.setEmail(person.getEmail());
            personDto.setAboutMe(person.getAboutMe());
            personDto.setJob(person.getJob());
            personDto.setLocation(person.getLocation());
            personDto.setBannerImage(person.getBannerImage());
            personDto.setProfileImage(person.getProfileImage());
            return personDto;  
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la persona " + id));
        return new ResponseEntity<PersonDTO>(personDto, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
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
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") Long id){
        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
