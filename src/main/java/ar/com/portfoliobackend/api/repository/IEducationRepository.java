package ar.com.portfoliobackend.api.repository;

import ar.com.portfoliobackend.api.model.Education;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEducationRepository extends JpaRepository<Education, Long>{
    List<Education> findByPersonId(Long personId);
}
