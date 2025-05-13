package com.example.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Converter.LineConverter;
import com.example.Converter.StationLineConverter;
import com.example.DTOs.StationLineDTO;
import com.example.Models.Line;
import com.example.Models.Station;
import com.example.Models.StationLine;
import com.example.Repository.LineRepository;
import com.example.Repository.StationLineRepository;
import com.example.Repository.StationRepository;

@Service
public class StationLineService {

    @Autowired
    private StationLineRepository stationLineRepository; // Assuming you have a StationLineRepository
    @Autowired
    private LineRepository lineRepository; // Assuming you have a LineRepository
    @Autowired
    private StationRepository stationRepository; // Assuming you have a StationRepository
    // @Autowired
    private LineConverter lineConverter; // Assuming you have a BusRepository
    @Autowired
    private StationLineConverter stationLineConverter; // Assuming you have a BusRepository
//הוספת stationLine
public Long addStationLine(StationLineDTO stationLineDTO) {

    // שליפת הקו לפי מספר
    Line line = lineRepository.findByNumber(stationLineDTO.getLineNumber());
    if (line == null) {
        throw new RuntimeException("Line with number " + stationLineDTO.getLineNumber() + " not found");
    }
            // System.out.println("-------------------"+line+"----------is null");

    // שליפת התחנה לפי שם
    Station station = stationRepository.findByName(stationLineDTO.getStationName());
    if (station == null) {
        throw new RuntimeException("Station '" + stationLineDTO.getStationName() + "' not found");
    }
            //   System.out.println("-------------------"+station+"----------is null");

    // שליפת כל תחנות הקו הזה לפי קו בלבד!
    List<StationLine> stationLinesForLine = stationLineRepository.findAll().stream()
        .filter(sl -> sl.getLine().getId() == line.getId())
        .collect(Collectors.toList());
// System.out.println("-------------------"+stationLinesForLine.size()+"----------is null");
    // הדפסת בדיקה
    // System.out.println("Updating stations with order >= " + stationLineDTO.getOrderInLine());

    // הזזה קדימה של תחנות קיימות מהסדר הנתון ומעלה
    for (StationLine sl : stationLinesForLine) {
        if (sl.getOrderInLine() >= stationLineDTO.getOrderInLine()) {
            sl.setOrderInLine(sl.getOrderInLine() + 1);
            stationLineRepository.save(sl);
        }
        
    }
    System.out.println("אחרי הפור");
    //שמירת תחנה חדשה
    StationLine newStationLine = stationLineConverter.toStationLine(stationLineDTO);
    stationLineRepository.save(newStationLine);
     System.out.println("אחרי save");
    return newStationLine.getId();

}


     public List<StationLineDTO> getAll()
    {
    return stationLineRepository.findAll().stream()
    .map(stationLineConverter::toStationLineDTO)
    .collect((Collectors.toList()));
    }
}
