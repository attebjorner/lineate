package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Recording;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecordingRepositoryTest {

    @Autowired
    private RecordingRepository underTest;

    @Test
    void itShouldFindByReleaseTimeBetween() {
        var start = ZonedDateTime.now().withYear(1980);
        var end = ZonedDateTime.now().withYear(1995);

        List<Recording> expected = underTest.findAllByReleaseTimeBetween(start, end);
        assertThat(expected.size()).isEqualTo(2);
    }
}
