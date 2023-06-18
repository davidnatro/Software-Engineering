package ru.hse.kpokollok.data.model;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.hse.kpokollok.data.entity.PassengerEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerModel {

    private Long id;
    private String fullName;

    public static PassengerModel fromEntity(PassengerEntity passenger) {
        return new PassengerModel(passenger.getId(), passenger.getFullName());
    }

    public static List<PassengerModel> fromEntities(Iterable<PassengerEntity> passengers) {
        List<PassengerModel> passengerModels = new LinkedList<>();

        for (final PassengerEntity passenger : passengers) {
            passengerModels.add(fromEntity(passenger));
        }

        return passengerModels;
    }
}
