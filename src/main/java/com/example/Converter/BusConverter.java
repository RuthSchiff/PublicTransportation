package com.example.Converter;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.example.DTOs.BusDTO;
import com.example.Models.Bus;
import com.example.Models.Travel;

public class BusConverter {

    // המרת BusDTO ל-Bus
    public static BusDTO convertToDto(Bus bus) {
        BusDTO busDto = new BusDTO();
        busDto.setId(bus.getId());
        busDto.setLicense_plate(bus.getLicense_plate());
        busDto.setSeats(bus.getSeats());
        busDto.setTravelsTimes(bus.getTravels().stream()
                .map(Travel::getDepature_time) // המרת זמן היציאה של כל נסיעה
                .collect(Collectors.toList()));
        return busDto;
    }

    public static Bus convertToModel(BusDTO busDto) {
        Bus bus = new Bus();
        bus.setId(busDto.getId());
        bus.setLicense_plate(busDto.getLicense_plate());
        bus.setSeats(busDto.getSeats());
        
        // if (busDto.getTravelsIds() != null) {
        //     List<Travel> travels = busDto.getTravelsIds().stream()
        //             .map(travelId -> new Travel()) // יצירת אובייקט Travel עם ה-ID
        //             .collect(Collectors.toList());
            // bus.setTravels(travels); // הגדרת רשימת הנסיעות
        // } else {
        //     bus.setTravels(new ArrayList<>()); // אם אין נסיעות, הגדר רשימה ריקה
        // }
        // המרת רשימת ה-IDs לרשימת אובייקטי Travel
        return bus;
    }

    public static List<BusDTO> convertToDtoList(List<Bus> buses) {
        if (buses == null) {
            return null;
        }
        List<BusDTO> busDtos = new ArrayList<>();
        for (Bus bus : buses) {
            busDtos.add(convertToDto(bus));
        }
        return busDtos;
    }

    // המרה מרשימת Bus ל-BusDTO
    public static List<Bus> convertToModelList(List<BusDTO> busDtos) {
        if (busDtos == null) {
            return null;
        }
        List<Bus> buses = new ArrayList<>();
        for (BusDTO busDto : busDtos) {
            buses.add(convertToModel(busDto));
        }
        return buses;
    }
}
