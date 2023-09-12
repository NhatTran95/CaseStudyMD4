package com.cg.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "hair_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HairService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @OneToMany (mappedBy = "hairService")
    private List<BookingDetail> bookingDetailLs;
}
