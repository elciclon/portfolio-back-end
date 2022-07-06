package ar.com.portfoliobackend.api.service;

import ar.com.portfoliobackend.api.model.Skill;
import ar.com.portfoliobackend.api.repository.ISkillRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {
    @Autowired
    ISkillRepository skillRepository;
    public Skill findById;
    public List<Skill> findByPersonId(Long personId) {
        return skillRepository.findByPersonId(personId);
    }
    
    public Skill saveSkill(Skill skill){
        return skillRepository.save(skill);
    }

    public Optional<Skill> findById(Long skillId) {
        return skillRepository.findById(skillId);
    }

    public void deleteSkillById(Long skillId) {
        skillRepository.deleteById(skillId);
    }
    
}
