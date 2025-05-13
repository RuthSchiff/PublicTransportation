// // package com.example.Converter;
// // import com.example.Models.Line;
// // import com.example.Models.Station;
// // import com.example.Models.StationLine;
// // import com.example.Repository.LineRepository;
// // import com.example.Repository.StationRepository;

// // import java.util.List;
// // import java.util.stream.Collectors;

// // import org.springframework.beans.factory.annotation.Autowired;

// // import com.example.DTOs.StationLineDTO;

// // public class StationLineConverter {
// //   @Autowired
// //   private static LineRepository lineRepository;
// //   private static StationRepository stationRepository;


// //   public static StationLineDTO convertToDto(StationLine stationLine) {
// //     StationLineDTO stationLineDTO = new StationLineDTO();
// //     stationLineDTO.setId(stationLine.getId());
// //     stationLineDTO.setOrderInLine(stationLine.getOrderInLine());
// //     stationLineDTO.setLineNumber(stationLine.getLine().getNumber());
// //     stationLineDTO.setStationName(stationLine.getStation().getName());
// //     stationLineDTO.setOrderInLine(stationLine.getOrderInLine());
// //     return stationLineDTO;
// //   }
  
// //   public static StationLine convertToModel(StationLineDTO stationLineDTO) {
// //     StationLine stationLine = new StationLine();
// //     stationLine.setId(stationLineDTO.getId());
// //     Line line = lineRepository.findByNumber(stationLineDTO.getLineNumber());
// //     Station station = stationRepository.findByName(stationLineDTO.getStationName());
// //     stationLine.setOrderInLine(stationLineDTO.getOrderInLine());
// //     stationLine.setLine(line);
// //     stationLine.setStation(station);
// //     return stationLine;
// //   }
// //   public static List<StationLineDTO> convertToDtoList(List<StationLine> stationLines) {
// //     return stationLines.stream()
// //         .map(StationLineConverter::convertToDto)
// //         .collect(Collectors.toList());
// //   }
// //   public static List<StationLine> convertToModelList(List<StationLineDTO> stationLineDTOs) {
// //     return stationLineDTOs.stream()
// //         .map(StationLineConverter::convertToModel)
// //         .collect(Collectors.toList());
    
// // }
// // }
// package com.example.Converter;

// import com.example.Models.Line;
// import com.example.Models.Station;
// import com.example.Models.StationLine;
// import com.example.Repository.LineRepository;
// import com.example.Repository.StationRepository;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.example.DTOs.StationLineDTO;

// @Component
// public class StationLineConverter {
//   @Autowired
//   private LineRepository lineRepository;

//   @Autowired
//   private StationRepository stationRepository;

//   public static StationLineDTO convertToDto(StationLine stationLine) {
//     StationLineDTO stationLineDTO = new StationLineDTO();
//     stationLineDTO.setId(stationLine.getId());
//     stationLineDTO.setOrderInLine(stationLine.getOrderInLine());
//     stationLineDTO.setLineNumber(stationLine.getLine().getNumber());
//     stationLineDTO.setStationName(stationLine.getStation().getName());
//     stationLineDTO.setOrderInLine(stationLine.getOrderInLine());
//     return stationLineDTO;
//   }
  
//   public static StationLine convertToModel(StationLineDTO stationLineDTO) {
//     StationLine stationLine = new StationLine();
//     stationLine.setId(stationLineDTO.getId());
//     Line line = lineRepository.findByNumber(stationLineDTO.getLineNumber());
//     Station station = stationRepository.findByName(stationLineDTO.getStationName());
//     stationLine.setOrderInLine(stationLineDTO.getOrderInLine());
//     stationLine.setLine(line);
//     stationLine.setStation(station);
//     return stationLine;
//   }
//     public static List<StationLineDTO> convertToDtoList(List<StationLine> stationLines) {
//     return stationLines.stream()
//         .map(StationLineConverter::convertToDto)
//         .collect(Collectors.toList());
//   }
//   public static List<StationLine> convertToModelList(List<StationLineDTO> stationLineDTOs) {
//     return stationLineDTOs.stream()
//         .map(StationLineConverter::convertToModel)
//         .collect(Collectors.toList());
    
// }

//   // public List<StationLineDTO> convertToDtoList(List<StationLine> stationLines) {
//   //   return stationLines.stream()
//   //       .map(StationLineConverter::convertToDto)
//   //       .collect(Collectors.toList());
//   // }

//   // public List<StationLine> convertToModelList(List<StationLineDTO> stationLineDTOs) {
//   //   return stationLineDTOs.stream()
//   //       .map(StationLineConverter::convertToModel)
//   //       .collect(Collectors.toList());
//   // }

// }




package com.example.Converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTOs.StationLineDTO;
import com.example.Models.Line;
import com.example.Models.Station;
import com.example.Models.StationLine;
import com.example.Repository.LineRepository;
import com.example.Repository.StationRepository;



@Service
public class StationLineConverter {
    
    
@Autowired
 private  LineRepository lineRepository;
@Autowired
 private  StationRepository stationRepository;

//המרת stationLine לstationLineDto
//     public  StationLineDTO toStationLineDTO(StationLine stationLine) {
//         StationLineDTO stationLineDTO = new StationLineDTO();
//         stationLineDTO.setId(stationLine.getId());
//         stationLineDTO.setLineNumber(stationLine.getLine().getNumber());
//         stationLineDTO.setStationName(stationLine.getStation().getName());
//         stationLine.setOrderInLine(stationLine.getOrderInLine());   
//         return stationLineDTO;
// }
public StationLineDTO toStationLineDTO(StationLine stationLine) {
    StationLineDTO stationLineDTO = new StationLineDTO();
    stationLineDTO.setId(stationLine.getId());
    stationLineDTO.setOrderInLine(stationLine.getOrderInLine());
    stationLineDTO.setLineNumber(stationLine.getLine().getNumber());
    // stationLineDTO.setStationName(stationLine.getStation().getName()); // אתחול לערך null
    // בדוק אם station הוא null
    if (stationLine.getStation() != null) {
        stationLineDTO.setStationName(stationLine.getStation().getName());
    } else {
        stationLineDTO.setStationName("Unknown Station"); // ערך ברירת מחדל
    }

    return stationLineDTO;
}

    //המרת stationLineDTO לstationLine
    public  StationLine toStationLine(StationLineDTO stationLineDTO) {
        StationLine stationLine = new StationLine();
        stationLine.setId(stationLineDTO.getId());
        Line line = lineRepository.findByNumber(stationLineDTO.getLineNumber());
        String lineNumber = stationLineDTO.getLineNumber();
        System.out.println("line: " + line.getNumber());
        Station station = stationRepository.findByName(stationLineDTO.getStationName());
        stationLine.setLine(line);
        stationLine.setStation(station);
        stationLine.setOrderInLine(stationLineDTO.getOrderInLine());
        return stationLine;
    }
    //המרת ��שי��ת stationLine לרשי��ת stationLineDTO
    public  List<StationLineDTO> toStationLineDTOList(List<StationLine> stationLines) {
        return stationLines.stream().map(stationLine -> toStationLineDTO(stationLine)).collect(Collectors.toList());
    }
    //המרת ��שי��ת stationLine לרשי��ת stationLine
    public  List<StationLine> toStationLineList(List<StationLineDTO> stationLineDTOs) {
        return stationLineDTOs.stream().map(stationLine -> toStationLine(stationLine)).collect(Collectors.toList());
    }
    }



   