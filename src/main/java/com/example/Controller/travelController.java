package com.example.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.DTOs.TravelDTO;
import com.example.Service.TravelService;

@RestController
@RequestMapping("/travels")
public class travelController {

    @Autowired
    private TravelService travelService;

    @PostMapping("/add")
    public ResponseEntity<Long> create(@RequestBody TravelDTO travelDTO) {
        Long result = travelService.add(travelDTO);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/debug")
    public ResponseEntity<String> debug() {
        return ResponseEntity.ok(travelService == null ? "travelService is NULL" : "travelService is OK");
    }

    @GetMapping("/getTravels")
    public ResponseEntity<List<TravelDTO>> getAllTravels() {
        List<TravelDTO> travels = travelService.getAllTravels(); // Call the getAllTravels method from TravelService
        return ResponseEntity.ok(travels); // Return the list of travels with a 200 OK status
    }

}
