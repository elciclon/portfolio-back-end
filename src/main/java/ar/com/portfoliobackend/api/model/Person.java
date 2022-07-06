package ar.com.portfoliobackend.api.model;

import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)private Long id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "email")
    private String email;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "job")
    private String job;
    @Column(name = "location")
    private String location;

    @Column(name = "banner_image", length = 2048)
    private String bannerImage;
    
    @Column(name = "url_image", length = 2048)
    private String profileImage;
    @NotNull
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="person", cascade = CascadeType.ALL)
    private List<Education> educations;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="person", cascade = CascadeType.ALL)
    private List<Experience> experiences;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="person", cascade = CascadeType.ALL)
    private List<Skill> skills;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="person", cascade = CascadeType.ALL)
    private List<Language> languages;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="person", cascade = CascadeType.ALL)
    private List<Project> projects;
    
}