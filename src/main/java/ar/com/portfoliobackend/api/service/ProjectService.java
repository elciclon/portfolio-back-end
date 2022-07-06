package ar.com.portfoliobackend.api.service;

import ar.com.portfoliobackend.api.model.Project;
import ar.com.portfoliobackend.api.repository.IProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    IProjectRepository projectRepository;
    public Project findById;
    public List<Project> findByPersonId(Long personId) {
        return projectRepository.findByPersonId(personId);
    }
    
    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public Optional<Project> findById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }
    
}
