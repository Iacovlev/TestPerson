package das.eu.internship.Test.services;

import das.eu.internship.Test.dto.TechPassportDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface TechPassportService {

    HttpStatus save(TechPassportDTO techPassportDTO);

    List<TechPassportDTO> getAll();

    TechPassportDTO getById(Long id);

    TechPassportDTO update(TechPassportDTO techPassportDTO);

    HttpStatus deleteById(Long id);


}
