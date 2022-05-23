package ar.com.portfoliobackend.api.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String dateOfBirth;
    private String nationality;
    private String email;
    private String aboutMe;
    private String job;
    private String location;

    @Column(name = "banner_image", length = 2048)
    private String bannerImage;
    
    @Column(name = "url_image", length = 2048)
    private String profileImage;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Adress> adresses;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Set<Education> educations;
    
    public void addEducation(Education education) {
        if (null == education) {
            educations = new HashSet<>();
        }
        educations.add(education);
        education.setPerson(this);
    }
    public void removeEducation(Education education) {
        educations.remove(education);
        education.setPerson(null);
    }
}
