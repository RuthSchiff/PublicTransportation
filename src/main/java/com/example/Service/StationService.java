package com.example.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.StationConverter;
import com.example.DTOs.StationDto;
import com.example.Models.Station;
import com.example.Repository.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository; // Assuming you have a StationRepository

    public Long add(StationDto stationDto) {
        StationConverter converter = new StationConverter();
        Station station = converter.convertToEntity(stationDto);
        if (!stationRepository.exists(Example.of(station))) {
            return stationRepository.save(station).getId();
        }

        return null;
    }

    public List<StationDto> getAllStations() {
        List<Station> stations = stationRepository.findAll(); // Get all stations from the database
        StationConverter converter = new StationConverter();
        return converter.convertToDtoList(stations);

    }

}
