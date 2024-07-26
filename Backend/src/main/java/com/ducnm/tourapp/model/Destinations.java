package com.ducnm.tourapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Destinations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private String address;
    private Double rate;
    private Long price;
    private List<String> image;
    private String slug;
    private boolean deleted = false;
    @Enumerated(EnumType.STRING)
    private DestinationType destinationType;
    @OneToMany(mappedBy = "destinations")
    private List<Schedules> schedulesList;
    @ManyToOne
    @JoinColumn(name = "cities_id")
    private Cities cities;
}
