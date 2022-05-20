package ar.com.portfoliobackend.api.repository;

import ar.com.portfoliobackend.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long>{
    
}