package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hair_services")
public class HairService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "haircutSchedule_id", referencedColumnName = "id", nullable = false)
    private HaircutSchedule haircutSchedule;

}
