package com.example.Converter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DTOs.TravelDTO;
import com.example.Models.Travel;
import com.example.Repository.BusRepository;
import com.example.Repository.DriverRepository;
import com.example.Repository.LineRepository;


@Component
public class TravelConverter {

    private final BusRepository busRepository;
    private final LineRepository lineRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public TravelConverter(BusRepository busRepository, LineRepository lineRepository, DriverRepository driverRepository) {
        this.busRepository = busRepository;
        this.lineRepository = lineRepository;
        this.driverRepository = driverRepository;
    }

    // המרת Travel ל-TravelDTO
    public TravelDTO toTravelDTO(Travel travel) {
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setId(travel.getId());
        travelDTO.setDepartureTime(travel.getDepature_time());
        travelDTO.setBus_id(travel.getBus().getId());
        travelDTO.setLine_id(travel.getLine().getId());
        travelDTO.setDriver_id(travel.getDriver().getId());
        return travelDTO;
    }

    // המרת TravelDTO ל-Travel
    public Travel toTravel(TravelDTO travelDTO) {
        Travel travel = new Travel();
        travel.setId(travelDTO.getId());
        travel.setDepature_time(travelDTO.getDepartureTime());
        travel.setBus(busRepository.findById(travelDTO.getBus_id()).orElse(null));
        travel.setLine(lineRepository.findById(travelDTO.getLine_id()).orElse(null));
        travel.setDriver(driverRepository.findById(travelDTO.getDriver_id()).orElse(null));
        return travel;
    }

    // רשימת Travel -> רשימת TravelDTO
    public List<TravelDTO> toTravelDTOList(List<Travel> travels) {
        return travels.stream().map(this::toTravelDTO).collect(Collectors.toList());
    }

    // רשימת TravelDTO -> רשימת Travel
    public List<Travel> toTravelList(List<TravelDTO> travelDTOs) {
        return travelDTOs.stream().map(this::toTravel).collect(Collectors.toList());
    }
}
