package com.example.DTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StationLineDTO {

    private Long id;
 
    private String lineNumber;

    private String stationName;

    private int orderInLine; 

}
