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

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "stationLines") // חשוב!
@Entity
@Table(name = "Station")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "station", fetch = FetchType.LAZY)
    private List<StationLine> stationLines;
}

