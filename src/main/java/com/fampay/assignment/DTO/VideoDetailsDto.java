package com.fampay.assignment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoDetailsDto {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("publishTime")
    private LocalDateTime publishTime;

    @JsonProperty("channelTitle")
    private String channelTitle;

    @JsonProperty("thumbnails")
    private ThumbnailsDto thumbnails;
}
