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

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @OneToOne
    private StylistImage stylistImage;

    @Enumerated(EnumType.STRING)
    private EStatusStylist status;

}
