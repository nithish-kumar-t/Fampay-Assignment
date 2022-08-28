package com.fampay.assignment.repository;

import com.fampay.assignment.Entity.VideoDetails;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoDetails, Long> {

    @Query(value = "SELECT video FROM VideoDetails video "+
            "WHERE video.description like %?1% " +
            "OR video.title like %?1%")
    List<VideoDetails> findAllByTitleOrDescription(String words, Sort sort);
}
