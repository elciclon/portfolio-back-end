package ar.com.portfoliobackend.api.repository;

import ar.com.portfoliobackend.api.model.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISkillRepository extends JpaRepository<Skill, Long>{
    List<Skill> findByPersonId(Long personId);
}
