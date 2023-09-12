package com.cg.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dayBooking;

    private LocalDateTime timeBooking;

    private String customerName;

    private String customerPhoneNumber;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetails;

    @ManyToOne
    private Stylist stylist;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private StatusBooking statusBooking;
}
