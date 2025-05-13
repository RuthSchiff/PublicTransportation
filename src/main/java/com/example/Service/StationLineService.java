package com.example.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Converter.LineConverter;
import com.example.Converter.StationLineConverter;
import com.example.DTOs.LineDTO;
import com.example.DTOs.StationLineDTO;
import com.example.Models.StationLine;
import com.example.Repository.LineRepository;
import com.example.Repository.StationLineRepository;
import com.example.Repository.StationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StationLineService {

    @Autowired
    private StationLineRepository stationLineRepository; // Assuming you have a StationLineRepository
    @Autowired
    private LineRepository lineRepository; // Assuming you have a LineRepository
    @Autowired
    private StationRepository stationRepository; // Assuming you have a StationRepository
    // @Autowired
    private LineConverter lineConverter; // Assuming you have a BusRepository
    @Autowired
    private StationLineConverter stationLineConverter; // Assuming you have a BusRepository

    public Long addStationLine(StationLineDTO stationLineDTO) {
        // Convert DTO to Model
        LineDTO lineDTO = LineConverter.convertToDTO(lineRepository.findByNumber(stationLineDTO.getLineNumber())); // Convert
        if(lineDTO == null)
        throw new EntityNotFoundException("No Line found with number");                                                                                              // Model
        for (Long lineId : lineDTO.getStationsLinesIdes()) {
            StationLineDTO stationLineDto = stationLineRepository.findById(lineId)
                    .map(stationLineConverter::toStationLineDTO) // Convert Model to DTO
                    .orElse(new StationLineDTO());
            if (stationLineDto.getOrderInLine() >= stationLineDTO.getOrderInLine()) { // If the station in the line is
                                                                                      // greater than the station
                                                                                      // entered
                stationLineDto.setOrderInLine(stationLineDto.getOrderInLine() + 1);
                // Update the order of the station in the line
                stationLineRepository.save(stationLineConverter.toStationLine(stationLineDTO)); // Save the updated
                                                                                                 // station line
            } // If the station in the line is less than the station entered, leave the order
              // of the station in the line
        }
        return stationLineRepository.save(stationLineConverter.toStationLine(stationLineDTO)).getId(); // This is                                                                                                  // correct
    }
     public List<StationLineDTO> getAll()
    {
    return stationLineRepository.findAll().stream()
    .map(stationLineConverter::toStationLineDTO)
    .collect((Collectors.toList()));
    }
}
