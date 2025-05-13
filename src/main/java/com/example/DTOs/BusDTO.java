package com.example.DTOs;
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
    // private  List<Long>  TravelsIds;
    // private List<Long> travelsIds;

    private List<Long> travelsIds = new ArrayList<>();

    public BusDTO() {} // חובה בנאי ריק
}
