package com.ducnm.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vouchers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private LocalDate startTime;
    private LocalDate endTime;
    private Long amount;
    private Long percent;
    private boolean deleted = false;
    @OneToMany(mappedBy = "vouchers", cascade = CascadeType.REMOVE)
    private List<Bookings> bookingsList;
}
