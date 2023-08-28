package com.cg.model;


import com.cg.model.dto.CustomerResDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")

public class Customer extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phone;


    @OneToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<HaircutSchedule> haircutSchedules;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", locationRegion=" + locationRegion +
                ", haircutSchedules=" + haircutSchedules +
                '}';
    }

    public CustomerResDTO toCustomerResDTO() {
        return new CustomerResDTO()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegionResDTO())
                ;
    }
}
