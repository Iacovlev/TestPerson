package das.eu.internship.Test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "person_profile")
@AllArgsConstructor
@NoArgsConstructor
public class PersonProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "age")
    private Integer age;

    @Column (name = "country")
    private String country;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "confirmPassword")
    private String confirmPassword;

}
