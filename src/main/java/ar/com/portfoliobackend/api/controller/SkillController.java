package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.exception.ResourceNotFoundException;
import ar.com.portfoliobackend.api.model.Skill;
import ar.com.portfoliobackend.api.service.PersonService;
import ar.com.portfoliobackend.api.service.SkillService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/person")
public class SkillController {
    @Autowired
    PersonService personService;
    
    @Autowired
    SkillService skillService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personId}/skills")
    public ResponseEntity<Skill> createSkill(@PathVariable(value = "personId") Long personId, 
                                                     @Valid                                         
                                                     @RequestBody Skill skillRequest) 
                                                     throws ResourceNotFoundException{
        Skill skill = personService.getPersonById(personId).map(person -> {
            skillRequest.setPerson(person);
            return skillService.saveSkill(skillRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la persona " + personId)); 
        return new ResponseEntity<Skill>(skill, HttpStatus.CREATED);
    }
    
    @GetMapping("/{personId}/skills")
    public ResponseEntity<List> getAllSkillsByPersonId(@PathVariable(value = "personId") Long personId){
        List<Skill> skills = skillService.findByPersonId(personId);
        return new ResponseEntity<List>(skills, HttpStatus.OK);
    }
    
    @GetMapping("/skills/{skillId}")
    public Optional<Skill> getSkillById(@PathVariable(value = "skillId") Long skillId){
        Optional<Skill> skill = skillService.findById(skillId);
        return skill;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/skills/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable("skillId") Long skillId, 
                                             @Valid
                                             @RequestBody Skill skill)
                                             throws ResourceNotFoundException{
            skillService.findById(skillId).map(skillData -> {
            Skill _skill = new Skill();
            _skill.setId(skillId);
            _skill.setSkill(skill.getSkill());
            _skill.setScore(skill.getScore());
            _skill.setPerson(skillData.getPerson());
            return skillService.saveSkill(_skill);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la habilidad " + skillId));
        return new ResponseEntity<Skill>(skill, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/skills/{skillId}")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("skillId") Long skillId){
        skillService.deleteSkillById(skillId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
