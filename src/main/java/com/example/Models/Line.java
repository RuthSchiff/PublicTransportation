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

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "stationLines") // חשוב מאוד!
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
    @OneToMany(mappedBy = "line", fetch = FetchType.LAZY)
    private List<StationLine> stationLines;

}