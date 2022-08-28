package com.fampay.assignment.controller;

import com.fampay.assignment.DTO.VideoDetailsDto;
import com.fampay.assignment.service.SearchApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class YoutubeSearchApiController {

    @Autowired
    private SearchApiService searchApiService;

    @GetMapping(path = "/search/{query}")
    @ResponseBody
    public List<VideoDetailsDto> searchVideos(@PathVariable String query,
                                           @RequestParam(value = "maxRecords", required = false, defaultValue = "10") int numOfRecords) {

        return searchApiService.findAllVideos(query, numOfRecords);
    }

}
