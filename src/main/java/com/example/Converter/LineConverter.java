package com.example.Converter;

import java.util.List;
import java.util.stream.Collectors;
import com.example.DTOs.LineDTO;
import com.example.Models.Line;
import com.example.Repository.StationLineRepository;
import com.example.Repository.TravelRepository;

public class LineConverter {

    private static StationLineRepository stationRepository; // Assuming you have a StationLineRepository to fetch
                                                            // stations
    private static TravelRepository travelRepository;

    public static LineDTO convertToDTO(Line line) {

        LineDTO lineDTO = new LineDTO();
        if (line == null) {
            throw new IllegalArgumentException("line is null in LineConverter");
        } else
            lineDTO.setId(line.getId());
        lineDTO.setDestination(line.getDestination());
        lineDTO.setSource(line.getSource());
        lineDTO.setNumber(line.getNumber());
        lineDTO.setTravelsId(line.getTravels().stream()
                .map(travel -> travel.getId())
                .collect(Collectors.toList()));
        lineDTO.setStationsLinesIdes(line.getStationLines().stream()
                .map(station -> station.getId())
                .collect(Collectors.toList()));
        return lineDTO;
    }

    public static Line convertToModel(LineDTO lineDTO) {
        Line line = new Line();
        line.setId(lineDTO.getId());
        line.setNumber(lineDTO.getNumber());
        line.setSource(lineDTO.getSource());
        line.setDestination(lineDTO.getDestination());
        // line.setTravels(lineDTO.getTravelsId().stream()
        // .map(travelId -> travelRepository.findById(travelId).orElse(null))
        // .collect(Collectors.toList()));
        // line.setStationLines(lineDTO.getStationsLinesIdes().stream()
        // .map(stationLineId -> stationRepository.findById(stationLineId).orElse(null))
        // .collect(Collectors.toList()));

        return line;
    }

    public static List<LineDTO> convertToDTOList(List<Line> lines) {
        return lines.stream()
                .map(LineConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Line> convertToModelList(List<LineDTO> lineDTOs) {
        return lineDTOs.stream()
                .map(LineConverter::convertToModel)
                .collect(Collectors.toList());
    }
}
