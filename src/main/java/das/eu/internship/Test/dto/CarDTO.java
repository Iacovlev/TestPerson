package das.eu.internship.Test.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String carBrand;
    private String fuel;
    private Long yearOfIssue;
    private String owner;
    private Long personId;
}
