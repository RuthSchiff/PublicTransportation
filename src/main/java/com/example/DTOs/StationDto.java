package com.example.DTOs;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StationDto {

    private Long id;
    private String name;
    // private String location; 
    private List<String> stationLinesNames =  new ArrayList<>(); // קווי התחנה

}
