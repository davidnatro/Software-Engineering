package ru.hse.kpokollok.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hse.kpokollok.data.entity.FlightEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightModel {

    private Long id;
    private String flightNumber;
    private String from;
    private String to;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime depatureTime;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime arrivalTime;

    public static FlightModel fromEntity(FlightEntity flight) {
        return new FlightModel(flight.getId(), flight.getFlightNumber(), flight.getFrom(),
                               flight.getTo(), flight.getDepatureTime(), flight.getArrivalTime());
    }

    public static List<FlightModel> fromEntities(Iterable<FlightEntity> flights) {
        List<FlightModel> flightModels = new LinkedList<>();

        for (final FlightEntity flightEntity : flights) {
            flightModels.add(fromEntity(flightEntity));
        }

        return flightModels;
    }
}
