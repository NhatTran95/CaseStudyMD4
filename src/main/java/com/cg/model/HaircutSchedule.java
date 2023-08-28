package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "haircut_schedule")
public class HaircutSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "stylist_id", referencedColumnName = "id", nullable = false)
    private Stylist stylist;

    private Date hourBook;

    private int quantityService;

    private BigDecimal totalPrice;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private HaircutScheduleStatus status;

    @OneToMany(mappedBy = "haircutSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HairServiceBooking> hairServiceBookings = new ArrayList<>();
}
