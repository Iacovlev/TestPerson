package das.eu.internship.Test.services.impl;


import das.eu.internship.Test.dto.PersonDTO;
import das.eu.internship.Test.entities.Person;
import das.eu.internship.Test.repository.PersonRepository;
import das.eu.internship.Test.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    @Override
    public HttpStatus save(PersonDTO personDTO) {
        Person person = modelMapper.map(personDTO, Person.class);
        Person savedPerson = personRepository.save(person);
        return savedPerson != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findAll().stream()
                .map(person -> modelMapper.map(person, PersonDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getById(Long id) {
        return personRepository.findById(id)
                .map(val ->modelMapper.map(val, PersonDTO.class))
                .orElse(null);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        Person newPerson = modelMapper.map(personDTO, Person.class);
        Optional<Person> optionalPerson = personRepository.findById(newPerson.getId());
        if(optionalPerson.isPresent()){
            Person oldPerson = optionalPerson.get();

            newPerson.setId(oldPerson.getId());

            personRepository.save(newPerson);

            return modelMapper.map(newPerson, PersonDTO.class);
        }
        return null;
    }

    @Override
    public HttpStatus deleteById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()){
            personRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}