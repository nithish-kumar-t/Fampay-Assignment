package com.fampay.assignment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ThumbnailsDto {
    @JsonProperty(value = "default")
    private Map<String, String> defaultType;

    @JsonProperty(value = "medium")
    private Map<String, String> medium;

    @JsonProperty(value = "high")
    private Map<String, String> high;
}
