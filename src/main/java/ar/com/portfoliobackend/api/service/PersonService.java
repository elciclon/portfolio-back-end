package ar.com.portfoliobackend.api.service;

import ar.com.portfoliobackend.api.model.Person;
import ar.com.portfoliobackend.api.repository.IPersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    
    @Autowired
    IPersonRepository personRepository;
    
    public void savePerson(Person person){
        personRepository.save(person);
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

}