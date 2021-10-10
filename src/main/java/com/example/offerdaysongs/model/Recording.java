package com.example.offerdaysongs.model;

import liquibase.pro.packaged.E;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recording implements NonNullPropertiesCopyable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String version;
    ZonedDateTime releaseTime;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    Singer singer;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "company_recording",
            joinColumns = @JoinColumn(name = "recording_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "company_id", nullable = false)
    )
    List<Company> company;
}
