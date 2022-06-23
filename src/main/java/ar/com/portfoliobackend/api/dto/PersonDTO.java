package ar.com.portfoliobackend.api.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO implements Serializable {
    private Long id;
    private String fullName;
    private String dateOfBirth;
    private String nationality;
    private String email;
    private String aboutMe;
    private String job;
    private String location;
    private String bannerImage;
    private String profileImage;    
}
