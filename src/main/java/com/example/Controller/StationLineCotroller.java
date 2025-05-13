package com.example.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DTOs.StationLineDTO;
import com.example.Service.StationLineService;
import com.example.Service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/StationLine")
public class StationLineCotroller {

    private final StationService stationService;
    @Autowired
    private StationLineService StationLineService;

    StationLineCotroller(StationService stationService) {
        this.stationService = stationService;
    }
  @PostMapping("/addStationLine")
    public ResponseEntity<Long> addStationLine(@RequestBody StationLineDTO stationLineDTO) {
        Long result = StationLineService.addStationLine(stationLineDTO);
        System.out.println("result: " + result);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getStationLine")
    public ResponseEntity<List<StationLineDTO>> getStationLine() {
        List <StationLineDTO> result = StationLineService.getAll();
        if(result != null) 
            return ResponseEntity.status(HttpStatus.OK).body(result);
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/deleteStationLine/{id}")
    public ResponseEntity<Void> removeStationLine(@PathVariable Long id) {
        if(StationLineService.deleteStationLine(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
   
}
