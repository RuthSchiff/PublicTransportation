package com.example.Controller;
import java.time.LocalTime;
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
import com.example.DTOs.LineDTO;
import com.example.Service.LineService;

@RestController
@RequestMapping("/lines")

public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping("/getLines")
    public List<LineDTO> gelinesDTOs() {
        return lineService.getLines();
    }

    @PostMapping("/addLine")
    public ResponseEntity<Long> createLine(@RequestBody LineDTO lineDTO) {
        Long results = lineService.addLine(lineDTO);
        if (results != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(results);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getBusesPlaces{lineNumber}")
    public ResponseEntity<String> getBusesPlaces(@PathVariable String lineNumber) {

        String results = lineService.getBusesPlaces(lineNumber);
        // if (results.equals("Line not found")) {
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(results);
        // }
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @GetMapping("/getStationsByLine{lineNumber}")
    public ResponseEntity<String> getStationsByLineFunc(@PathVariable String lineNumber) {

        String results = lineService.getStationsByLine(lineNumber);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    // לקבלת כל נסיעות הקו
    @GetMapping("/getTravelsByLine{lineNumber}")
    public ResponseEntity<List<LocalTime>> getTravelsByLineFunc(@PathVariable String lineNumber) {

        List<LocalTime> results = lineService.getTravelsByLine(lineNumber);

        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    // לקבלת כל נסיעות הקו לפי זמן
    @GetMapping("/getTravelsByLineAndTime/{lineNumber}/{time}")
    public ResponseEntity<List<LocalTime>> getTravelsByLineAndTimeFunc(@PathVariable String lineNumber,
            @PathVariable LocalTime time) {

        List<LocalTime> results = lineService.getTravelTimesInNextHour(lineNumber, time);

        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
}
