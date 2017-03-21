package com.spd.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FACILITY")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @OneToMany(mappedBy = "facility")
    private List<AnnouncementFacility> announcementFacilities;

}
