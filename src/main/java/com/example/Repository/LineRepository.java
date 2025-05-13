package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Models.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

   Line findByNumber(String number); 

   Line findById(long id); // Assuming you have a method to find by ID
   
}