package das.eu.internship.Test.services.impl;

import das.eu.internship.Test.dto.TechPassportDTO;
import das.eu.internship.Test.entities.Car;
import das.eu.internship.Test.entities.TechPassport;
import das.eu.internship.Test.repository.TechPassportRepository;
import das.eu.internship.Test.services.TechPassportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechPassportImpl implements TechPassportService {
    private final ModelMapper modelMapper;
    private final CarServiceImpl carService;
    private final TechPassportRepository techPassportRepository;
    @Override
    public HttpStatus save(TechPassportDTO techPassportDTO) {
        TechPassport techPassport = modelMapper.map(techPassportDTO, TechPassport.class);
        techPassport.setCar(modelMapper.map(carService.getById(techPassportDTO.getCarId()), Car.class));
        TechPassport savedTechPassport = techPassportRepository.save(techPassport);
        return savedTechPassport==null?HttpStatus.BAD_REQUEST:HttpStatus.OK;
    }

    @Override
    public List<TechPassportDTO> getAll() {
        return techPassportRepository.findAll().stream()
                .map(techPassport -> modelMapper.map(techPassport, TechPassportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TechPassportDTO getById(Long id) {
        return techPassportRepository.findById(id)
                .map(techPassport -> modelMapper.map(techPassport, TechPassportDTO.class))
                .orElse(null);
    }

    @Override
    public TechPassportDTO update(TechPassportDTO techPassportDTO) {
        TechPassport newTechPassport = modelMapper.map(techPassportDTO, TechPassport.class);
        Optional<TechPassport> optionalTechPassport = techPassportRepository.findById(newTechPassport.getId());
        if(optionalTechPassport.isPresent()){
            TechPassport oldTechPassport = optionalTechPassport.get();

            newTechPassport.setId(oldTechPassport.getId());
            newTechPassport.setCar(oldTechPassport.getCar());

            techPassportRepository.save(newTechPassport);

            return modelMapper.map(newTechPassport, TechPassportDTO.class);
        }
        return null;
    }

    @Override
    public HttpStatus deleteById(Long id) {
        Optional<TechPassport> oldTechPassportOpt = techPassportRepository.findById(id);
        if(oldTechPassportOpt.isPresent()){
            techPassportRepository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
