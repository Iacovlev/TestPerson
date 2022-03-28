package das.eu.internship.Test.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "techpassport")
public class TechPassport {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 
 @Column (name = "engine_number")
 private Long engineNumber;

 @OneToOne (mappedBy = "techPassport")
 private Car car;


 @Column (name = "color")
 private String color;
}
