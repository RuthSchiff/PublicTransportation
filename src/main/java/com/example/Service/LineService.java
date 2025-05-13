package com.example.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.DriverConverter;
import com.example.Converter.LineConverter;
import com.example.DTOs.DriverDTO;
import com.example.DTOs.LineDTO;
import com.example.Models.Driver;
import com.example.Models.Line;
import com.example.Repository.LineRepository;


@Service
public class LineService {
    @Autowired
    private LineRepository lineRepository; // Assuming you have a LineRepository
    
        public Long addLine(LineDTO lineDTO) {
        Line line = LineConverter.convertToModel(lineDTO); // Convert DTO to Model
        if(!lineRepository.exists(Example.of(line))) {
           return lineRepository.save(line).getId(); // This is correct
        }
        return null; // Return null if the bus already exists
    }
    public List<LineDTO> getLines() {
        List<Line> lines = lineRepository.findAll(); // Fetch all drivers from the repository
        return LineConverter.convertToDTOList(lines); // Convert to DTO list
    }
    
}
