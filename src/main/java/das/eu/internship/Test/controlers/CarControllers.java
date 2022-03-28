package das.eu.internship.Test.controlers;


import das.eu.internship.Test.dto.CarDTO;
import das.eu.internship.Test.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services/car")
public class CarControllers {
    private final CarService carService;

    @PostMapping("/save")
    public HttpStatus save(@RequestBody CarDTO carDTO){
        return carService.save(carDTO);
    }

    @GetMapping("/all")
    public List<CarDTO> getAll(){
        return carService.getAll();
    }

    @GetMapping("/getById/{id}")
    public CarDTO getById(@PathVariable Long id){
        return carService.getById(id);
    }

    @PutMapping("/update")
    public CarDTO update(@RequestBody CarDTO carDTO){
        return carService.update(carDTO);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteById(@PathVariable Long id) {
        return carService.deleteById(id);
    }
}
