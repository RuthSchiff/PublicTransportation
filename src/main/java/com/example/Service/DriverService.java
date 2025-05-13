package com.example.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.DriverConverter;
import com.example.DTOs.DriverDTO;
import com.example.Models.Driver;
import com.example.Repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository; // Assuming you have a DriverRepository

       public Long addDriver(DriverDTO driverDTO) {
        Driver driver = DriverConverter.convertToModel(driverDTO); // Convert DTO to Model
        if(!driverRepository.exists(Example.of(driver))) {
           return driverRepository.save(driver).getId(); // This is correct
        }
        return null; // Return null if the bus already exists
    }

    public List<DriverDTO> getDrivers() {
        List<Driver> drivers = driverRepository.findAll(); // Fetch all drivers from the repository
        return DriverConverter.convertToDTOList(drivers); // Convert to DTO list
    }

    
}
