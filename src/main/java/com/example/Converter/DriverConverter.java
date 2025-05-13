package com.example.Converter;
import com.example.Models.Driver;
import com.example.Repository.TravelRepository;
import java.util.List;
import java.util.stream.Collectors;
import com.example.DTOs.DriverDTO;

public class DriverConverter {

    private static TravelRepository travelRepository;
    // המרת DriverDTO ל-Driver
    public static DriverDTO convertToDTO(Driver driver) {
        List<Long> travelsList = driver.getTravels().stream()
                .map(line -> line.getId())
                .collect(Collectors.toList());
        DriverDTO driverDto = new DriverDTO();
        driverDto.setId(driver.getId());
        driverDto.setName(driver.getName());
        driverDto.setPhoneNumber(driver.getPhoneNumber());
        return driverDto;
    }
    // המרה מDriverDTO ל-Driver מסוג Model
    public static Driver convertToModel(DriverDTO driverDto) {
        Driver driver = new Driver();
        driver.setId(driverDto.getId());
        driver.setName(driverDto.getName());
        driver.setPhoneNumber(driverDto.getPhoneNumber());
        return driver;
    }  
    // המרה מרשימת Driver ל-DriverDTO
    public static List<DriverDTO> convertToDTOList(List<Driver> drivers) {
        return drivers.stream()
                .map(DriverConverter::convertToDTO)
                .collect(Collectors.toList());
    }
    // המרה מרשימת DriverDTO ל-Driver
    public static List<Driver> convertToModelList(List<DriverDTO> driverDtos) {
        return driverDtos.stream()
                .map(DriverConverter::convertToModel)
                .collect(Collectors.toList());
    }
}
