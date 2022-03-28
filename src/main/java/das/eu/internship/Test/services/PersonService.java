package das.eu.internship.Test.services;

import das.eu.internship.Test.dto.PersonDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PersonService {
    HttpStatus save(PersonDTO personDTO);

    List<PersonDTO> getAll();

    PersonDTO getById(Long id);

    PersonDTO update(PersonDTO personDTO);

    HttpStatus deleteById(Long id);

}
