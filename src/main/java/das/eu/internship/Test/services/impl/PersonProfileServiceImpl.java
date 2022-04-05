package das.eu.internship.Test.services.impl;

import das.eu.internship.Test.dto.PersonProfileCreateDTO;
import das.eu.internship.Test.dto.PersonProfileDTO;
import das.eu.internship.Test.entities.PersonProfile;
import das.eu.internship.Test.repository.PersonProfileRepository;
import das.eu.internship.Test.services.PersonProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonProfileServiceImpl implements PersonProfileService {

    private final ModelMapper modelMapper;

    private final PersonProfileRepository repository;

    @Override
    public PersonProfileDTO save(PersonProfileCreateDTO personProfileDTO) {
        PersonProfile savedProfile = repository.save(modelMapper.map(personProfileDTO, PersonProfile.class));
        return modelMapper.map(savedProfile, PersonProfileDTO.class);
    }

    @Override
    public PersonProfileDTO update(PersonProfileDTO personProfileDto) {
        return null;
    }

    @Override
    public boolean delete(Integer profileId) {
        return false;
    }

    @Override
    public List<PersonProfileDTO> getAll() {
        return null;
    }

    @Override
    public PersonProfileDTO getById(Integer id) throws Exception {
        Optional<PersonProfile> optionalPersonProfile = repository.findById(id);
        if (optionalPersonProfile.isEmpty()){
            throw new Exception(String.format("Profile with id %s not found", id));
        }
        return modelMapper.map(optionalPersonProfile.get(),PersonProfileDTO.class);
    }
    }

