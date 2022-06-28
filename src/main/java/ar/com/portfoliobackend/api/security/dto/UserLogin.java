package ar.com.portfoliobackend.api.security.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
