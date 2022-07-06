package ar.com.portfoliobackend.api.repository;

import ar.com.portfoliobackend.api.model.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<Project, Long>{
    List<Project> findByPersonId(Long personId);
}
