package com.example.Models;

import java.util.List;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "Station")

public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
//לכל תחנה יש הרבה קווים
@OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
private List<StationLine> stationLines;

}
