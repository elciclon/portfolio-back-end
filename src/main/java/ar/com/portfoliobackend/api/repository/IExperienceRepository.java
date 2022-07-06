package ar.com.portfoliobackend.api.repository;

import ar.com.portfoliobackend.api.model.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExperienceRepository extends JpaRepository<Experience, Long>{
    List<Experience> findByPersonId(Long personId);
}
