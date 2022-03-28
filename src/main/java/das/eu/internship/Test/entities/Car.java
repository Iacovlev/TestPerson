package das.eu.internship.Test.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "car_brand")
    private  String carBrand;

    @Column (name = "fuel")
    private String fuel;

    @Column (name = "year_of_issue")
    private Long yearOfIssue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "techPassport_id", referencedColumnName = "id")
    private TechPassport techPassport;

}
