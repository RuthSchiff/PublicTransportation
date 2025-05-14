package com.example.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DTOs.StationDto;
import com.example.Service.StationService;

@RestController
@RequestMapping("/stations")

public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/getLines")
    public List<StationDto> getStationsDTOs() {
        return stationService.getAllStations();
    }
    @PostMapping("/addStation")
    public ResponseEntity<Long> createStation(@RequestBody StationDto stationDto) {
        Long results = stationService.add(stationDto);
        if (results != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(results);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/searchByStation({stationNumber}/{lineNumber}")
    public ResponseEntity<String> searchByStation(@PathVariable Long stationNumber, @PathVariable String lineNumber) {
       if(lineNumber.equals("*")){
            return ResponseEntity.status(HttpStatus.OK).body(stationService.SearchByStation(stationNumber));
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(stationService.SearchByStation(stationNumber, lineNumber));
       }
    }
    
}
