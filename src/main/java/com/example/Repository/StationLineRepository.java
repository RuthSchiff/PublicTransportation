package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Models.Line;
import com.example.Models.Station;
import com.example.Models.StationLine;

@Repository
public interface StationLineRepository extends JpaRepository<StationLine, Long> {

    Line findById(long id);

    // Station findByName(String lineName);
    // public List<StationLineDTO> getLines();
}