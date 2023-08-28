package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hair_services_booking")
public class HairServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hair_service_id",referencedColumnName = "id",nullable = false)
    private HairService hairService;

    // Many-to-One relationship with HaircutSchedule
    @ManyToOne
    @JoinColumn(name = "haircut_schedule_id",referencedColumnName = "id", nullable = false)
    private HaircutSchedule haircutSchedule;

    // Constructors, getters, setters
}
