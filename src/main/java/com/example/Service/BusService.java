package com.example.Service;

import com.example.Repository.StationRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.BusConverter;
import com.example.Converter.LineConverter;
import com.example.Converter.StationLineConverter;
import com.example.DTOs.BusDTO;
import com.example.DTOs.LineDTO;
import com.example.DTOs.StationLineDTO;
import com.example.Models.Bus;
import com.example.Models.Line;
import com.example.Repository.BusRepository;
import com.example.Repository.LineRepository;
import com.example.Repository.StationLineRepository;


@Service
public class BusService {

    
    @Autowired
    private BusRepository busRepository;
    private LineRepository lineRepository;
    private StationLineRepository stationLineRepository;


    public Long addBus(BusDTO busDTO) {
        Bus bus = BusConverter.convertToModel(busDTO);
        if(!busRepository.exists(Example.of(bus))) {
           return busRepository.save(bus).getId(); // This is correct
        }
        return null; // Return null if the bus already exists
    }



    public List<BusDTO> getBuses() {
        return busRepository.findAll().stream()
                .map(BusConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
