package com.example.Service;

import com.example.Repository.StationRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.example.Converter.BusConverter;
import com.example.Converter.LineConverter;
import com.example.Converter.StationLineConverter;
import com.example.DTOs.BusDTO;
import com.example.DTOs.LineDTO;
import com.example.DTOs.StationLineDTO;
import com.example.Models.Bus;
import com.example.Models.Line;
import com.example.Repository.BusRepository;
import com.example.Repository.LineRepository;
import com.example.Repository.StationLineRepository;

// @Service
// public class BusService {

//     private static StationRepository stationRepository;
//     private static BusRepository busRepository;
// @Autowired
// private StationLineRepository stationLineRepository;
// private static LineRepository lineRepository;

// public static void addBus(BusDTO busDTO) {
//     // המרת BusDTO ל-Bus
//     Bus bus = BusConverter.convertToModel(busDTO);
//     // שמירת האוטובוס במסד הנתונים
//     busRepository.save(bus);
// }

// // // פונקציה להוספת תחנה לקו
// // public Long addStationToLine(StationLineDTO stationLineDTO) {
// //     // בדוק אם הקו קיים
// //    if(stationLineDTO.getId()== null) {
// //         return null; // הקו לא קיים, החזר null
// //     }
// //     // בדוק אם התחנה קיימת
// //     Line line = lineRepository.findById(stationLineDTO.getId()).orElse(null);
// //     if (line == null) {
// //         return null; // התחנה לא קיימת, החזר null
// //     }
// //     StationLine stationLine = new StationLine();  
// //     LineDTO  lineDTO = LineConverter.convertToDTO(line); // המר את ה-DTO לאובייקט קו
// //     // הוסף את התחנה לקו
// //     for (Long lineId : lineDTO.getStationsLinesIdes()) {

// //      stationLine = stationLineRepository.findById(lineId).orElse(new StationLine()); // חפש את התחנה בקו

// //      if(stationLine>= stationLineDTO.getOrderInLine()) { // אם התחנה בקו גדולה מהתחנה שהוזנה
// //          stationLine.setOrderInLine(stationLine.getOrderInLine()+1); // עדכן את הסדר של התחנה בקו
// //      } // אם התחנה בקו קטנה מהתחנה שהוזנה, השאר את הסדר של התחנה בקו

// //         line.setId(line.getId()+1); // עדכן את ה-ID של הקו 

// //     }

// //     return lineId; // החזרת ה-ID של הקו

// // },

// // public Long addStationLine(StationLineDTO newStationLine) {
// //     // שלב 1: מצא את הקו לפי מזהה והמר אותו ל-DTO
// //     Line line = lineRepository.findById(newStationLine.getId()).orElse(null);
// //     if (line == null) {
// //         return null; // אם הקו לא נמצא, החזר null
// //     }

// //     LineDTO lineDTO = LineConverter.convertToDTO(line);

// //     // שלב 2: עבור על כל התחנות הקיימות בקו
// //     for (Long stationLineId : lineDTO.getStationsLinesIdes()) {
// //         // הבא את התחנה בקו לפי מזהה והמר ל-DTO
// //         StationLineDTO existingStationLine = stationLineRepository.findById(stationLineId)
// //                 .map(StationLineConverter::convertToDto)
// //                 .orElse(null);

// //         if (existingStationLine == null) {
// //             continue; // אם לא נמצאה תחנה, דלג
// //         }

// //         // שלב 3: אם סדר התחנה הנוכחית שווה או גדול מהתחנה החדשה – הגדל את הסדר שלה באחד
// //         if (existingStationLine.getOrderInLine() >= newStationLine.getOrderInLine()) {
// //             existingStationLine.setOrderInLine(existingStationLine.getOrderInLine() + 1);
// //         }
// //     }

// //     // שלב 4: שמור את התחנה החדשה במסד הנתונים (המרת DTO ליישות)
// //     StationLine stationLineEntity = StationLineConverter.convertToEntity(newStationLine);
// //     StationLine saved = stationLineRepository.save(stationLineEntity);

// //     // שלב 5: החזר את המזהה של התחנה החדשה שנוספה
// //     return saved.getId();
// // }

// // פונקציה להוספת תחנה לקו
// //מקבלת תחנת מסוג StationLineDTO
// public Long addStationLine(StationLineDTO stationLine){
//     //חיפוש את התחנה קיימת 
//     LineDTO lineDTO = LineConverter.convertToDTO(lineRepository.findById(stationLine.getId()).orElse(null)); // המר את ה-DTO לאובייקט קו
//     //יצירת אובייקט חדש של תחנה בקו
//     StationLineDTO stationLineDTO = new StationLineDTO(); // צור אובייקט חדש של תחנה בקו
//     for (Long lineId : lineDTO.getStationsLinesIdes()) { // עבור על כל התחנות בקו
//         StationLineDTO stationLineDTO1 = stationLineRepository.findById(lineId).map(
//             StationLineConverter:: convertToDto).orElse(new StationLineDTO()); // המר את התחנה לאובייקט תחנה בקו
//         if(stationLineDTO1.getOrderInLine()>= stationLine.getOrderInLine()) { // אם התחנה בקו גדולה מהתחנה שהוזנה

//                 stationLineDTO1.setOrderInLine(stationLineDTO1.getOrderInLine()+1); // עדכן את הסדר של התחנה בקו
//                 stationLineRepository.save(StationLineConverter.convertToModel(stationLineDTO1)); // שמור את התחנה בקו
//             } // אם התחנה בקו קטנה מהתחנה שהוזנה, השאר את הסדר של התחנה בקו
//     }
//     return stationLineRepository.save(StationLineConverter.convertToModel(stationLine)).getId(); // שמור את התחנה בקו והחזר את ה-ID שלה
// }
@Service
public class BusService {

    
    @Autowired
    private BusRepository busRepository;
    private LineRepository lineRepository;
    private StationLineRepository stationLineRepository;


    public Long addBus(BusDTO busDTO) {
        Bus bus = BusConverter.convertToModel(busDTO);
        if(!busRepository.exists(Example.of(bus))) {
           return busRepository.save(bus).getId(); // This is correct
        }
        return null; // Return null if the bus already exists
    }

    // public Long addStationLine(StationLineDTO stationLineDTO) {
    //     Line line = lineRepository.findById(stationLineDTO.getId()).orElse(null);
    //     if (line == null) {
    //         return null;
    //     }

    //     LineDTO lineDTO = LineConverter.convertToDTO(line);
    //     for (Long lineId : lineDTO.getStationsLinesIdes()) {
    //         StationLineDTO stationLineDTO1 = stationLineRepository.findById(lineId)
    //                 .map(StationLineConverter::toStationLineDTO)
    //                 .orElse(new StationLineDTO());

    //         if (stationLineDTO1.getOrderInLine() >= stationLineDTO.getOrderInLine()) {
    //             stationLineDTO1.setOrderInLine(stationLineDTO1.getOrderInLine() + 1);
    //             stationLineRepository.save(StationLineConverter(stationLineDTO1));
    //         }
    //     }

    //     return stationLineRepository.save(StationLineConverter.convertToModel(stationLineDTO)).getId();
    // }

    public List<BusDTO> getBuses() {
        return busRepository.findAll().stream()
                .map(BusConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
