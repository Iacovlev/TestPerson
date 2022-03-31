package das.eu.internship.Test.controlers;

import das.eu.internship.Test.dto.PersonDTO;
import das.eu.internship.Test.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service/person")
@RequiredArgsConstructor
public class PersonControlers {
    private final PersonService personService;

    @PostMapping("/save")
    public PersonDTO savePerson(@RequestBody PersonDTO personDTO){
        return personService.savePerson(personDTO);
    }
    @GetMapping("/all")
    public List<PersonDTO> getAll(){
        return personService.getAll();
    }

    @GetMapping("/getById/{id}")
    public PersonDTO getById(@PathVariable Long id){
        return personService.getById(id);
    }

    @PutMapping("/update")
    public PersonDTO update(@RequestBody PersonDTO personDTO){
        return personService.update(personDTO);
    }


    @DeleteMapping("/deleteById/{id}")
    public HttpStatus deleteById(   @PathVariable Long id){
        return personService.deleteById(id);
    }
}
