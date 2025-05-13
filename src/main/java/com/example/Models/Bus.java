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

@Data//מייצר את כל המתודות הבסיסיות כמו equals, hashCode, toString
@Entity//הגדרת מחלקה כישות
@Table(name = "Bus")//הגדרת שם הטבלה בבסיס הנתונים
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String license_plate;
    private String seats;
    //לכל אוטובוס יש הרבה נסיעות
    @OneToMany(mappedBy = "bus", fetch = FetchType.LAZY)
    //מפתח זר שמחבר בין טבלת Travel לטבלת Bus
    private  List<Travel>  travels;
}