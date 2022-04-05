package das.eu.internship.Test.services;

import das.eu.internship.Test.dto.PersonProfileCreateDTO;
import das.eu.internship.Test.dto.PersonProfileDTO;

import java.util.List;

public interface PersonProfileService {
    PersonProfileDTO save(PersonProfileCreateDTO personProfileDto);
    PersonProfileDTO update(PersonProfileDTO personProfileDto);
    boolean delete(Integer profileId);
    List<PersonProfileDTO> getAll();
    PersonProfileDTO getById(Integer id) throws Exception;
}
