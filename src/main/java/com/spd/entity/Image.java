package com.spd.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "IMAGE")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "DATA")
    private Byte[] data;

    @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<AnnouncementImage> announcementImages;

}
