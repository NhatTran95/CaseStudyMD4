package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    private Date time;

    @OneToMany(mappedBy = "hairService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HairServiceBooking> hairServiceBookings = new ArrayList<>() ;
}
