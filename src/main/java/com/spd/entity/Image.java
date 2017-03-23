package com.spd.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    @Column(name = "DATA")
    private byte[] data;

   /* @OneToMany(mappedBy = "image", fetch = FetchType.LAZY)
    private List<AnnouncementImage> announcementImages;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
