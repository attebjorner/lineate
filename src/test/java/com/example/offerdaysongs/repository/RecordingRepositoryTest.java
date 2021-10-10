package com.example.offerdaysongs.repository;

import com.example.offerdaysongs.model.Recording;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RecordingRepositoryTest
{
    @Autowired
    private RecordingRepository underTest;

    @Test
    void shouldFindByReleaseTimeBetween()
    {
        var start = ZonedDateTime.now().withYear(1980);
        var end = ZonedDateTime.now().withYear(1995);

        List<Recording> expected = underTest.findAllByReleaseTimeBetween(start, end);
        assertThat(expected.size()).isEqualTo(2);
    }

    @Test
    void shouldFindByCompanyName()
    {
        var companyName = "The best company";

        List<Recording> expected = underTest.findAllByCompanyName(companyName);
        assertThat(expected.size()).isEqualTo(1);
    }

    @Test
    void shouldFindMinPriceByRecordingId()
    {
        int expected = underTest.getMinPriceByRecording(4);

        assertThat(expected).isEqualTo(400);
    }
}
