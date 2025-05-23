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
import com.example.DTOs.DriverDTO;
import com.example.Service.DriverService;


@RestController
@RequestMapping("/drivers")

public class DriverController {

    @Autowired
    private DriverService driverService;
    // Add your endpoint methods here, for example:
    @GetMapping("/getDrivers")
    public List<DriverDTO> getDriverDTOs() {
        return driverService.getDrivers();
    }
    @PostMapping("/addDriver")
    public ResponseEntity<Long> createDriver(@RequestBody DriverDTO driverDTO) {
        Long results = driverService.addDriver(driverDTO);
        if (results != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(results);
        }
        return ResponseEntity.badRequest().build();
    }
}
