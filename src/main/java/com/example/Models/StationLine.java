package com.example.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Data
// @Entity
// @Table(name = "StationLine")

// public class StationLine {

//     @Id
//     @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
//     private Long id;

//     //לקו אחד יש הרבה תחנות
//     @ManyToOne
//     @JoinColumn(name = "line_id")
//     private Line line;

//     //לכל תחנה יש הרבה קווים
//     @ManyToOne
//     @JoinColumn(name = "station_id")
//     private Station station;

//     private int orderInLine; // מיקום התחנה בקו

// }


@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"station", "line"}) // חשוב!
@Entity
@Table(name = "StationLine")
public class StationLine {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    private int orderInLine;
}

