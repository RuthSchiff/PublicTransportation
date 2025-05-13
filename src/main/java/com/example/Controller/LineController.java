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
    
    
}
