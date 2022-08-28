package com.fampay.assignment.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "thumbnails")
public class ThumbnailDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "default_type")
    @JsonProperty(value = "default")
    @ElementCollection
    private Map<String, String> defaultType;

    @Column(name = "medium_type")
    @JsonProperty(value = "medium")
    @ElementCollection
    private Map<String, String> medium;

    @Column(name = "high_type")
    @JsonProperty(value = "high")
    @ElementCollection
    private Map<String, String> high;

    @OneToOne(mappedBy = "thumbnails")
    private VideoDetails videoDetails;

}
