
package ar.com.portfoliobackend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "education")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String institution;
    private String degree;
    private String career;
    private int score;
    private int start;
    private int end;
    
    @Column(name = "url_image", length = 2048)
    private String urlImage;
    
    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Person person;

    
    
    
}
