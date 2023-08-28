package com.cg.model;

import com.cg.model.dto.CustomerResDTO;
import com.cg.model.dto.StylistResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stylists")

public class Stylist extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stylistName;

    private String phone;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private StylistStatus stylistStatus;

    public StylistResDTO toStylistResDTO() {
        return new StylistResDTO()
                .setId(id)
                .setStylistName(stylistName)
                .setPhone(phone)
                .setStylistStatus(stylistStatus)
                ;
    }
}
