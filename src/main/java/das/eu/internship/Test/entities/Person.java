package das.eu.internship.Test.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Long age;

    @Column(name = "address")
    private Long address;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Car> cars;
}


