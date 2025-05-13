package com.example.DTOs;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusDTO {

    private Long id;
    private String License_plate;
    private String Seats;
 

    private List<Time> travelsTimes = new ArrayList<>();

    public BusDTO() {} // חובה בנאי ריק
}
