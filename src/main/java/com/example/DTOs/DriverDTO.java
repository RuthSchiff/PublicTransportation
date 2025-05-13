package com.example.DTOs;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DriverDTO {

    private Long id;
    private String name;
    private String phoneNumber;
    private List<Long> travelsId;

}
