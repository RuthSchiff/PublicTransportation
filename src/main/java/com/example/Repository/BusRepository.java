package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    // Bus findByNumber(String number); // Assuming you have a method to find by bus number

    Bus findById(long id); // Assuming you have a method to find by ID
    
}
