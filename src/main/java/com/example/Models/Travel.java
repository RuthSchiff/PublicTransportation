package com.example.Models;
import java.sql.Time;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "Travel")
public class Travel {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    

    //לכל אוטובוס יש הרבה נסיעות
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    //הרבה נסיעות יכולות להיות לנהג אחד
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    //הרבה נסיעות יכולות להיות לקו אחד
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    //זמן יציאה
    private Time depature_time;

}
