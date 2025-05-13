package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.Converter.TravelConverter;
import com.example.DTOs.TravelDTO;
import com.example.Models.Travel;
import com.example.Repository.TravelRepository;



@Service
public class TravelService {

    private final TravelRepository travelRepository;
    private final TravelConverter travelConverter;

    @Autowired
    public TravelService(TravelRepository travelRepository, TravelConverter travelConverter) {
        this.travelRepository = travelRepository;
        this.travelConverter = travelConverter;
    }

    // פונקציה להוספת נסיעה
    public Long add(TravelDTO travelDTO) {
        Travel travel = travelConverter.toTravel(travelDTO);
        if (!travelRepository.exists(Example.of(travel))) {
            return travelRepository.save(travel).getId();
        }
        return null;
    }
    public List<TravelDTO> getAllTravels() {
        List<Travel> travels = travelRepository.findAll(); // קבל את כל הנסיעות מבסיס הנתונים
        return travelConverter.toTravelDTOList(travels);// המר את הרשימה ל-DTO
    }
}
