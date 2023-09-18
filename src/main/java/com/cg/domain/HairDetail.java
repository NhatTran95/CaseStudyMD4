package com.cg.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "hair_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HairDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @OneToMany (mappedBy = "hairDetail")
    private List<BookingDetail> bookingDetailLs;

    @OneToOne
    private HairDetailImage hairDetailImage;

    public HairDetail(Long id){
        this.id = id;
    }

}
