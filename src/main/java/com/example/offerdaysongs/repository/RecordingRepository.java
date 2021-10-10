package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface RecordingRepository extends JpaRepository<Recording, Long>, JpaSpecificationExecutor<Recording>
{
    @Query("FROM Recording WHERE releaseTime BETWEEN :startDate AND :endDate")
    List<Recording> findAllByReleaseTimeBetween(@Param("startDate") ZonedDateTime startDate,
                                                @Param("endDate") ZonedDateTime endDate);

    @Query("SELECT DISTINCT r FROM Company c JOIN c.recordings r where c.name = :name order by r.title")
    List<Recording> findAllByCompanyName(@Param("name") String name);

    @Query("SELECT MIN(c.recordingPrice) FROM Company c JOIN c.recordings r where r.id = :id")
    Integer getMinPriceByRecording(long id);
}
