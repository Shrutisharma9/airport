package com.example.airport.data.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "runways")
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class RunwayEntity implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id")
    private AirportEntity airport;

    @Column(name = "length_ft")
    private Integer lengthFeet;

    @Column(name = "width_ft")
    private Integer widthFeet;

    @Column(name = "name")
    private String name;

    @Column(name = "surface")
    private String surface;

    @Column(name = "lighted")
    private Boolean lighted;

    @Column(name = "closed")
    private Boolean closed;

    @Column(name = "le_ident")
    private String identification;

    @Column(name = "le_longitude_deg")
    private Double longitude;

    @Column(name = "le_latitude_deg")
    private Double latitude;

    @Column(name = "le_elevation_ft")
    private Double elevationFeet;

    @Column(name = "le_heading_degT")
    private Double heading;

    @Column(name = "le_displaced_threshold_ft")
    private Double displacedThresholdFeet;

    @Column(name = "he_ident")
    private String identificationHe;

    @Column(name = "he_latitude_deg")
    private Double latitudeHe;

    @Column(name = "he_longitude_deg")
    private Double longitudeHe;

    @Column(name = "he_elevation_ft")
    private Double elevationFeetHe;

    @Column(name = "he_heading_degT")
    private Double headingHe;

    @Column(name = "he_displaced_threshold_ft")
    private Double displacedThresholdFeetHe;
}
