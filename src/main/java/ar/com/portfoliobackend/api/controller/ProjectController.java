package ar.com.portfoliobackend.api.controller;

import ar.com.portfoliobackend.api.exception.ResourceNotFoundException;
import ar.com.portfoliobackend.api.model.Project;
import ar.com.portfoliobackend.api.service.PersonService;
import ar.com.portfoliobackend.api.service.ProjectService;
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
public class ProjectController {
    @Autowired
    PersonService personService;
    
    @Autowired
    ProjectService projectService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{personId}/projects")
    public ResponseEntity<Project> createProject(@PathVariable(value = "personId") Long personId, 
                                                 @Valid                                         
                                                 @RequestBody Project projectRequest) 
                                                 throws ResourceNotFoundException{
        Project project = personService.getPersonById(personId).map(person -> {
            projectRequest.setPerson(person);
            return projectService.saveProject(projectRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe la persona " + personId)); 
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
    
    @GetMapping("/{personId}/projects")
    public ResponseEntity<List> getAllProjectsByPersonId(@PathVariable(value = "personId") Long personId){
        List<Project> projects = projectService.findByPersonId(personId);
        return new ResponseEntity<List>(projects, HttpStatus.OK);
    }
    
    @GetMapping("/projects/{projectId}")
    public Optional<Project> getProjectById(@PathVariable(value = "projectId") Long projectId){
        Optional<Project> project = projectService.findById(projectId);
        return project;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable("projectId") Long projectId, 
                                                 @Valid
                                                 @RequestBody Project project)
                                                 throws ResourceNotFoundException{
            projectService.findById(projectId).map(projectData -> {
            Project _project = new Project();
            _project.setId(projectId);
            _project.setProjectTitle(project.getProjectTitle());
            _project.setProjectDescription(project.getProjectDescription());
            _project.setProjectDate(project.getProjectDate());
            _project.setProjectLink(project.getProjectLink());
            _project.setUrlImage(project.getUrlImage());
            _project.setPerson(projectData.getPerson());
            return projectService.saveProject(_project);
        }).orElseThrow(() -> new ResourceNotFoundException("No existe el proyecto " + projectId));
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("projectId") Long projectId){
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
