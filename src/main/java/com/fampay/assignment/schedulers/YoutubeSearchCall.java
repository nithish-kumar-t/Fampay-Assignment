package com.fampay.assignment.schedulers;

import com.fampay.assignment.Entity.VideoDetails;
import com.fampay.assignment.repository.VideoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Configuration
@EnableScheduling
public class YoutubeSearchCall {

    @Autowired
    private Environment env;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ObjectMapper mapper;

    WebTarget webTarget;

    String nextPageToken;

    Logger logger = LoggerFactory.getLogger(YoutubeSearchCall.class);


    @Scheduled(fixedDelay = 10000)
    public List<VideoDetails> makeCall() {
        webTarget = getWebTarget();

        Response response = webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get();

        if (response.getStatus() != 200) {
            return new ArrayList<>();
        }

        List<VideoDetails> videoDetailsList = new ArrayList<>();
        Map responseMap = response.readEntity(Map.class);
        nextPageToken = responseMap.get("nextPageToken").toString();

        ((List)responseMap.get("items")).forEach(detailObj -> {
            Long vidId = (long) ((Map)detailObj).get("id").hashCode();

            if (videoRepository.findById(vidId).isEmpty()) {
                VideoDetails videoDetails = mapper.convertValue(((Map) detailObj).get("snippet"), VideoDetails.class);
                videoDetails.setId(vidId);
                videoRepository.save(videoDetails);
                videoDetailsList.add(videoDetails);
            }
        });

        logger.info(responseMap.toString());

        return videoDetailsList;
    }

    private WebTarget getWebTarget() {
        List<String> keys =env.getProperty("authKey", List.class);
        String apiKey = keys.get(new Random().nextInt(keys.size()));

        if (webTarget != null) {
            return webTarget
                    .queryParam("key", apiKey)
                    .queryParam("pageToken", nextPageToken);
        }
        WebTarget webTarget = ClientBuilder.newClient().target(env.getProperty("youtubeURI"));

        webTarget = webTarget
                .queryParam("part", "snippet")
                .queryParam("maxResults", 20)
                .queryParam("q", "cricket")
                .queryParam("order", "date")
                .queryParam("publishAfter", "1970-01-01T00:00:00Z")
                .queryParam("type", "video")
                .queryParam("key", apiKey);
        return webTarget;
    }
}
