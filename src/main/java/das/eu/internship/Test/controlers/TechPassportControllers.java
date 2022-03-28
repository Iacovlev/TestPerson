package das.eu.internship.Test.controlers;


import das.eu.internship.Test.dto.TechPassportDTO;
import das.eu.internship.Test.services.TechPassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services/techpassport")
public class TechPassportControllers {
    private final TechPassportService techPassportService;

    @PostMapping("/save")
    public HttpStatus save(@RequestBody TechPassportDTO techPassportDTO){
        return techPassportService.save(techPassportDTO);
    }

    @GetMapping("/all")
    public List<TechPassportDTO> getAll(){
        return techPassportService.getAll();
    }

    @GetMapping("/getById/{id}")
    public TechPassportDTO getById(@PathVariable Long id){

        return techPassportService.getById(id);
    }

    @PutMapping("/update")
    public TechPassportDTO update(@RequestBody TechPassportDTO techPassportDTO){

        return techPassportService.update(techPassportDTO);
    }

    @DeleteMapping("/deleteById/{id}")
    public HttpStatus deleteById(@PathVariable Long id){

        return techPassportService.deleteById(id);
    }
}
