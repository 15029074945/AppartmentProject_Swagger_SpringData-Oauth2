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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnnouncementFacility> getAnnouncementFacilities() {
        return announcementFacilities;
    }

    public void setAnnouncementFacilities(List<AnnouncementFacility> announcementFacilities) {
        this.announcementFacilities = announcementFacilities;
    }
}
