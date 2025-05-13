package com.example.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DTOs.StationLineDTO;
import com.example.Service.StationLineService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/StationLine")
public class StationLineCotroller {
    @Autowired
    private StationLineService StationLineService;

    @PostMapping("/add")
    public ResponseEntity<Long> createStationLine(@RequestBody StationLineDTO stationLineDTO) {

        Long result = StationLineService.addStationLine(stationLineDTO);
        if(result != null) 
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getStationLine")
    public ResponseEntity<List<StationLineDTO>> getStationLine() {
        List <StationLineDTO> result = StationLineService.getAll();
        if(result != null) 
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }
    
    
}
