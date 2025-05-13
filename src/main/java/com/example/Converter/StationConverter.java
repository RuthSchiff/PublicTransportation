package com.example.Converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.DTOs.StationDto;
import com.example.Models.Station;
import com.example.Repository.StationLineRepository;

public class StationConverter {

    @Autowired
    private StationLineRepository stationLineRepository;

    // Assuming you have a LineRepository to fetch lines
    // Convert Station entity to StationDto
    public StationDto convertToDto(Station station) {
        StationDto stationDto = new StationDto();
        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        // stationDto.setLocation(station.getLocation());
        stationDto.setStationLinesNumbers(station.getStationLines().stream()
                .map(line -> line.getLine().getNumber())
                .collect(Collectors.toList()));
        return stationDto;
    }

    // Convert StationDto to Station entity
    public Station convertToEntity(StationDto stationDto) {
        Station station = new Station();
        station.setId(stationDto.getId());
        station.setName(stationDto.getName());
        // station.setLocation(stationDto.getLocation());
        // station.setStationLines(stationDto.getStationLinesIdes().stream()
        //         .map(lineId -> stationLineRepository.findById(lineId).orElse(null))
        //         .collect(Collectors.toList()));
        // station.se(station.getStationLines().stream()
        //         .map(station_line -> station_line.getLine().getNumber())
        //         .collect(Collectors.toList()));  
        
        


        return station;
    }

    // Convert a list of Station entities to a list of StationDto
    public List<StationDto> convertToDtoList(List<Station> stations) {
        return stations.stream()
                .map(station -> this.convertToDto(station))
                .collect(Collectors.toList());
    }
//    public List<StationDto> convertToDtoList(List<Station> stations) {
//         return stations.stream()
//                 .map(StationConverter::convertToDto)
//                 .collect(Collectors.toList());
//     }
    // Convert a list of StationDto to a list of Station entities
    public List<Station> convertToEntityList(List<StationDto> stationDtos) {
        return stationDtos.stream()
                .map(StationDto -> this.convertToEntity(StationDto))
                .collect(Collectors.toList());        
    }

}
