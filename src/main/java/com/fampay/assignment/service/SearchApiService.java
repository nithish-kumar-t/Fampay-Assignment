package com.fampay.assignment.service;

import com.fampay.assignment.DTO.VideoDetailsDto;
import com.fampay.assignment.Entity.VideoDetails;
import com.fampay.assignment.repository.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SearchApiService {

    @Autowired
    private VideoRepository videoRepository;

    public List<VideoDetailsDto> findAllVideos(String query, int maxRecords) {
        Set<String> words = new HashSet<>(Arrays.asList(query.split(" ")));
        Set<VideoDetails> allVideos = new HashSet<>();

        words.forEach(word -> {
           allVideos.addAll(videoRepository
                   .findAllByTitleOrDescription(word, Sort.by("publishTime").descending()));
        });

        return convertEntityToDto(allVideos);
    }

    private List<VideoDetailsDto> convertEntityToDto(Set<VideoDetails> videoDetails) {
        List<VideoDetailsDto> convertedList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        videoDetails.forEach(videos -> {
            convertedList.add(modelMapper.map(videos, VideoDetailsDto.class));
        });

        return convertedList;
    }

}
