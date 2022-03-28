package das.eu.internship.Test.services;

import das.eu.internship.Test.dto.CarDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CarService {
    HttpStatus save(CarDTO carDTO);

    List<CarDTO> getAll();

    CarDTO getById(Long id);

    CarDTO update(CarDTO carDTO);

    HttpStatus deleteById(Long id);

}
