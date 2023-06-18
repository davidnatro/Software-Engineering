package ru.hse.kpokollok.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "flights", schema = "kpo")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "from_point")
    private String from;

    @Column(name = "to_point")
    private String to;

    @Column(name = "depature_time")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime depatureTime;

    @Column(name = "arrival_time")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime arrivalTime;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "passenger_flight",
            joinColumns = @JoinColumn(name = "flight_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id", referencedColumnName = "id"))
    private Set<PassengerEntity> passengers = new HashSet<>();
}
