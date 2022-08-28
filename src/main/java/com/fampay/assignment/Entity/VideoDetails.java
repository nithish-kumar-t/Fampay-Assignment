package com.fampay.assignment.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "video_details", indexes = {
        @Index(name= "ind_publish_time", columnList = "publishTIme DESC")
})
public class VideoDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    private String title;

    private String description;

    @JsonProperty("publishTime")
    private LocalDateTime publishTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_details_id", referencedColumnName = "id")
    private ThumbnailDetails thumbnails;

    @JsonProperty("channelTitle")
    private String channelTitle;
}
