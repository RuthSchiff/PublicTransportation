package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Models.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    
}
