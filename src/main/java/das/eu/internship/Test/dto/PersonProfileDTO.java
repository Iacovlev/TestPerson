package das.eu.internship.Test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonProfileDTO {
    private Long id;
    private String name;
    private Long age;
    private String country;
    private String email;
    private String password;
    private String confirmPassword;
}
