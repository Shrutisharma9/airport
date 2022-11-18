package com.example.airport.data.model;

import lombok.*;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Indexed
@Entity
@Table(name = "countries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CountryEntity implements Serializable {

    //the unique id of this entity class
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Field(termVector = TermVector.YES)
    @Column(name = "code")
    private String code;

    @Field(termVector = TermVector.YES)
    @Column(name = "name")
    private String name;

    @Column(name = "continent")
    private String continent;

    @Column(name = "wikipedia_link")
    private String wikipedia_Link;

    @Column(name = "keywords")
    private String keywords;

    @OneToMany(mappedBy = "country")
    private Set<AirportEntity> airports;

}

