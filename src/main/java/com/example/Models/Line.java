package com.example.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import java.util.List;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "Line")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String source;
    private String destination;

    // לכל קו יש הרבה נסיעות
    @OneToMany(mappedBy = "line", fetch = FetchType.LAZY)
    private List<Travel> travels;

    // לכל קו יש הרבה תחנות
    // @ManyToMany
    // @JoinTable(name = "Line_Station", joinColumns = @JoinColumn(name =
    // "line_id"), inverseJoinColumns = @JoinColumn(name = "station_id"))
    // private List<StationLine> stationLines;
    // Line.java
    @OneToMany(mappedBy = "line", fetch = FetchType.LAZY)
    private List<StationLine> stationLines;

}