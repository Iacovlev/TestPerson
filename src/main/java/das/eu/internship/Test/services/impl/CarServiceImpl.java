package das.eu.internship.Test.services.impl;


import das.eu.internship.Test.dto.CarDTO;
import das.eu.internship.Test.entities.Car;
import das.eu.internship.Test.entities.Person;
import das.eu.internship.Test.repository.CarRepository;
import das.eu.internship.Test.services.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final ModelMapper modelMapper;
    private final PersonServiceImpl personService;
    private final CarRepository carRepository;

    @Override
    public HttpStatus save(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        car.setPerson(modelMapper.map(personService.getById(carDTO.getPersonId()), Person.class));
        Car savedCar = carRepository.save(car);
        return savedCar==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
    }

    @Override
    public List<CarDTO> getAll() {
        return carRepository.findAll().stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO getById(Long id) {
        return carRepository.findById(id)
                .map(car -> modelMapper.map(car, CarDTO.class))
                .orElse(null);
    }

    @Override
    public HttpStatus deleteById(Long id) {
        Optional<Car> oldCarOpt = carRepository.findById(id);
        if(oldCarOpt.isPresent()){
            carRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }


    @Override
    public CarDTO update(CarDTO carDTO) {
        Car newCar = modelMapper.map(carDTO, Car.class);
        Optional<Car> optionalCar = carRepository.findById(newCar.getId());
        if(optionalCar.isPresent()){
            Car oldCar = optionalCar.get();

            newCar.setId(oldCar.getId());
            newCar.setPerson(oldCar.getPerson());

            carRepository.save(newCar);

            return modelMapper.map(newCar, CarDTO.class);
        }
        return null;
    }
}