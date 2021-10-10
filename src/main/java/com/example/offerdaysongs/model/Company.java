package com.example.offerdaysongs.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Company implements NonNullPropertiesCopyable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "company_recording",
            joinColumns = @JoinColumn(name = "company_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "recording_id", nullable = false)
    )
    List<Recording> recordings;
    @Column(name = "recording_price")
    Integer recordingPrice;
}
