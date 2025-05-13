package com.example.Models;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;

     //לכל נהג יש הרבה נסיעות
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    //מפתח זר שמחבר בין טבלת Travel לטבלת Driver
    private List<Travel> travels;

 
}





