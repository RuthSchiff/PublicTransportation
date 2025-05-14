package com.example.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.DriverConverter;
import com.example.Converter.LineConverter;
import com.example.DTOs.DriverDTO;
import com.example.DTOs.LineDTO;
import com.example.Models.Driver;
import com.example.Models.Line;
import com.example.Models.Station;
import com.example.Models.StationLine;
import com.example.Models.Travel;
import com.example.Repository.LineRepository;
import com.example.Repository.StationLineRepository;

@Service
public class LineService {
    @Autowired
    private LineRepository lineRepository; // Assuming you have a LineRepository
    @Autowired
    private StationLineRepository stationLineRepository; // Assuming you have a StationLineRepository

    public Long addLine(LineDTO lineDTO) {
        Line line = LineConverter.convertToModel(lineDTO); // Convert DTO to Model
        if (!lineRepository.exists(Example.of(line))) {
            return lineRepository.save(line).getId(); // This is correct
        }
        return null; // Return null if the bus already exists
    }

    // public String getBusesPlaces(String lineNumber) {

    // // System.out.println("in loop");
    // // חיפוש ה line
    // Line line = lineRepository.findByNumber(lineNumber); // Fetch the line by
    // line number
    // //
    // if (line == null) {
    // return "Line not found"; // Return an error message if the line is not found
    // }
    // // חיפוש הסטיישן ליין
    // List<StationLine> stationLines = line.getStationLines().stream()
    // .sorted((s1, s2) -> Integer.compare(s1.getOrderInLine(),
    // s2.getOrderInLine()))
    // .collect(Collectors.toList());
    // //חיפוש הtravels
    // List<Travel> travels = line.getTravels().stream().sorted((t1, t2) ->
    // t1.getDepature_time().compareTo(t2.getDepature_time()))
    // .collect(Collectors.toList());
    // //ה string של המייקום של האוטובוסים
    // String StringGetBusesPlaces = "מיקום האוטובוסים בקו " + lineNumber + "\n";
    // //זמן נוכחי
    // Time currentTime = Time.valueOf(LocalTime.now());

    // int index;

    // // for(int i=0; i<travels.size(); i++){

    // // System.out.println("in the for");
    // // if(currentTime.getTime() - travels.get(i).getDepature_time().getTime() /
    // (1000 * 60) < stationLines.size()){

    // // index = (int) ((currentTime.getTime() -
    // travels.get(i).getDepature_time().getTime()) / (1000*60));
    // // StringGetBusesPlaces += "קו" + lineNumber+" " + " נמצא בקרבת התחנה" +
    // stationLines.get(i).getStation().getName() + "\n";
    // // }
    // // // if(currentTime.getTime() == travels.get(i).getDepature_time().getTime()
    // ){
    // // // StringGetBusesPlaces += "The bus number " + lineNumber + " is at
    // station number " + stationLines.get(i).getStation().getName() + "\n";
    // // // }
    // // }

    // return StringGetBusesPlaces; // Return the string with bus locations
    // }

    public String getBusesPlaces(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "Line not found";
        }

        List<StationLine> stationLines = line.getStationLines().stream()
                .sorted(Comparator.comparingInt(StationLine::getOrderInLine))
                .collect(Collectors.toList());

        List<Travel> travels = line.getTravels().stream()
                .sorted(Comparator.comparing(Travel::getDepature_time))
                .collect(Collectors.toList());

        String result = "מיקום האוטובוסים בקו " + lineNumber + "\n";
        Time currentTime = Time.valueOf(LocalTime.now());

        for (Travel travel : travels) {
            // הזמן הנוכחי - הזמן של היציאה
            long minutesSinceDeparture = (currentTime.getTime() - travel.getDepature_time().getTime()) / (1000 * 60);
            // בדוק אם הזמן שחלף הוא בטווח של תחנות הקו
            if (minutesSinceDeparture >= 0 && minutesSinceDeparture < stationLines.size()) {
                // המרת הזמן שחלף לאינדקס של התחנה
                int index = (int) minutesSinceDeparture;
                //
                Station station = stationLines.get(index).getStation();
                result += "קו " + lineNumber + " נמצא בקרבת התחנה " + station.getName() + "\n";
            }
        }

        return result;
    }

    // שליפת כל תחנות הקו

    public String getStationsByLine(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return "Line not found";
        }
        List<StationLine> stationLines = line.getStationLines().stream()
                .sorted(Comparator.comparingInt(StationLine::getOrderInLine))
                .collect(Collectors.toList());
        StringBuilder result = new StringBuilder("תחנות בקו " + lineNumber + ":\n");
        for (StationLine stationLine : stationLines) {
            Station station = stationLine.getStation();
            result.append("תחנה: ").append(station.getName()).append("\n");
        }
        return result.toString();
    }

    // לקבלת כל נסיעות הקו
    public List<LocalTime> getTravelsByLine(String lineNumber) {
        Line line = lineRepository.findByNumber(lineNumber);
        if (line == null) {
            return null; // Return null if the line is not found
        }
        List<Travel> travels = line.getTravels().stream()
                .sorted(Comparator.comparing(Travel::getDepature_time))
                .collect(Collectors.toList());

        List<LocalTime> travelTimes = travels.stream()
                .map(travel -> travel.getDepature_time().toLocalTime())
                .collect(Collectors.toList());
        return travelTimes;
    }

    //חיפוש נסיעה לפי שעה
    // public List<Travel> getTravelsByTime(String lineNumber, LocalTime time) {
    //     Line line = lineRepository.findByNumber(lineNumber);
    //     if (line == null) {
    //         return null; // Return null if the line is not found
    //     }
    //     List<Travel> travels = line.getTravels().stream()
    //             .filter(travel -> travel.getDepature_time().toLocalTime().equals(time))
    //             .collect(Collectors.toList());
    //     return travels;
    // }
//    public List<LocalTime> getTravelTimesByRoundedHour(String lineNumber, LocalTime time) {
//     Line line = lineRepository.findByNumber(lineNumber);
//     if (line == null) {
//         return Collections.emptyList(); // החזרה בטוחה
//     }

//     // עיגול השעה לשעה עגולה (00 דקות)
//     LocalTime roundedHour = time.withMinute(0).withSecond(0).withNano(0);
//     LocalTime nextHour = roundedHour.plusHours(1);

//     return line.getTravels().stream()
//             .map(travel -> travel.getDepature_time().toLocalTime())
//             .filter(depTime -> !depTime.isBefore(roundedHour) && depTime.isBefore(nextHour))
//             .sorted()
//             .collect(Collectors.toList());
// }
public List<LocalTime> getTravelTimesInNextHour(String lineNumber, LocalTime time) {
    Line line = lineRepository.findByNumber(lineNumber);
    if (line == null) {
        return Collections.emptyList(); // אין קו כזה
    }

    LocalTime oneHourLater = time.plusHours(1);

    return line.getTravels().stream()
            .map(travel -> travel.getDepature_time().toLocalTime())
            .filter(depTime -> !depTime.isBefore(time) && depTime.isBefore(oneHourLater))
            .sorted()
            .collect(Collectors.toList());
}




    public List<LineDTO> getLines() {
        List<Line> lines = lineRepository.findAll(); // Fetch all drivers from the repository
        return LineConverter.convertToDTOList(lines); // Convert to DTO list
    }

}
