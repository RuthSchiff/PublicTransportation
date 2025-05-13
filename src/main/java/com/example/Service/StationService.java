package com.example.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.StationConverter;
import com.example.DTOs.StationDto;
import com.example.Models.Line;
import com.example.Models.Station;
import com.example.Models.StationLine;
import com.example.Models.Travel;
import com.example.Repository.LineRepository;
import com.example.Repository.StationRepository;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository; // Assuming you have a StationRepository
    @Autowired
    private LineRepository lineRepository; // Assuming you have a LineRepository

    public Long add(StationDto stationDto) {
        StationConverter converter = new StationConverter();
        Station station = converter.convertToEntity(stationDto);
        if (!stationRepository.exists(Example.of(station))) {
            return stationRepository.save(station).getId();
        }
        return null;
    }

    public List<StationDto> getAllStations() {
        List<Station> stations = stationRepository.findAll(); // Get all stations from the database
        StationConverter converter = new StationConverter();
        return converter.convertToDtoList(stations);
    }

    // יש לשלוף את התחנה וזמני יציאה.
    // ולהוריד מהזמן הנוכחי את הזמן של יציאת הקו + מספר התחנות עד שיגיע לתחנה
    // הנוכחית
    public String SearchByStation(Long stationNumber, String lineNumber) {
        // חיפוש תחנה

        Station station = stationRepository.findById(stationNumber).orElse(null);
        if (station == null) {
            return "Station not found";
        }
        // חיפוש קו
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "Line not found";
        }
        // שליפת כל נסיעות הקו
        List<Travel> travels = line.getTravels();
        //זמן נוכחי
        Time currentTime = Time.valueOf(LocalTime.now());
        // מיון לפי זמן יציאה
        travels.sort(Comparator.comparingLong(
                travel -> Math.abs(currentTime.getTime() - travel.getDepature_time().getTime())));
        // החזרת זמני הגעה
        String arrivelTimes = "קו " + line.getNumber() + " - יגיע לתחנה " + station.getName() + "\n";

        // שליפת ה stationLine

        StationLine stationLine = station.getStationLines().stream()
                .filter(stationL -> stationL.getLine().getNumber().equals(lineNumber.toString())) // המרת lineNumber                                                                                               // ל-String
                .findFirst().orElse(null);

        if (stationLine == null) {
            return "StationLine not found";
        }
        // בדיקת זמן
        for (Travel travel : travels) {
            // המרת זמן היציאה ל-LocalTime
            LocalTime departureLocalTime = travel.getDepature_time().toLocalTime();

            // הוספת הדיליי לפי סדר התחנה במסלול
            LocalTime arrivalLocalTime = departureLocalTime.plusMinutes(stationLine.getOrderInLine());

            // המרת הזמן המשוער חזרה ל-Time
            Time estimatedArrivalTime = Time.valueOf(arrivalLocalTime);

            // בדיקת אם הזמן הנוכחי קטן מהזמן המשוער
            if (currentTime.compareTo(estimatedArrivalTime) <= 0) {
                long diffInMillis = estimatedArrivalTime.getTime() - currentTime.getTime();
                long diffMinutes = diffInMillis / (1000 * 60);
                arrivelTimes += " יגיע לתחנה בעוד: " + diffMinutes + " דקות\n";
            }
        }
        return arrivelTimes;
    }



    //לשליפה של כל הקווים של תחנה מסוימת
    public String SearchByStation(Long stationNumber) {
        // חיפוש תחנה
        Station station = stationRepository.findById(stationNumber).orElse(null);
        if (station == null) {
            return "Station not found";
        }
        // שליפת כל הקווים של התחנה
        List<StationLine> stationLines = station.getStationLines();

        String StationLinesString = "תחנה " + station.getName() + " - קווים:\n";
        for(StationLine stationLine : stationLines){

          StationLinesString += "\n" + SearchByStation(stationNumber, stationLine.getLine().getNumber());      
        }
        return StationLinesString;     
    }


}
