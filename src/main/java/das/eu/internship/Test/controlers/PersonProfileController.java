package das.eu.internship.Test.controlers;

import das.eu.internship.Test.dto.PersonProfileCreateDTO;
import das.eu.internship.Test.dto.PersonProfileDTO;
import das.eu.internship.Test.services.PersonProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service/person-profile")
@RequiredArgsConstructor
public class PersonProfileController {
    private final PersonProfileService personProfileService;

    @PostMapping("/")
    public PersonProfileDTO save(@RequestBody PersonProfileCreateDTO personProfileDTO){
        return personProfileService.save(personProfileDTO);
    }

    @GetMapping("/{id}")
    public PersonProfileDTO getById(@PathVariable Integer id) throws Exception{
        return personProfileService.getById(id);
    }
}