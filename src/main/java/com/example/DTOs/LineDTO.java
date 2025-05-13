package com.example.DTOs;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LineDTO {

  
    private Long id;
    private String number;
    private String source;
    private String destination;
    private List<Long> travelsId;
    //קווי התחנה
    private List<Long> stationsLinesIdes;

}
