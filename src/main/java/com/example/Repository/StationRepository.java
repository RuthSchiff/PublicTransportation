package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    
    Station findByName(String name); 

    // String SearchByStation(Long stationNumber, String lineNumber); // This method should be implemented in the service laye
  
}
