package com.cg.domain;

import com.cg.domain.Enum.EGender;
import com.cg.domain.Enum.EStatusStylist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stylists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private EGender gender;

    @OneToOne
    private StylistImage stylistImage;

    private EStatusStylist status;

}
