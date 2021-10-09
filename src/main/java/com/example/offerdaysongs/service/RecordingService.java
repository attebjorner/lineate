package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.dto.requests.CreateRecordingRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.repository.RecordingRepository;
import com.example.offerdaysongs.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecordingService {
    private static final String NOT_FOUND = "Recording with id %d not found";
    private final RecordingRepository recordingRepository;
    private final SingerRepository singerRepository;

    @Autowired
    public RecordingService(RecordingRepository recordingRepository,
                            SingerRepository singerRepository) {
        this.recordingRepository = recordingRepository;
        this.singerRepository = singerRepository;
    }

    public List<Recording> getAll() {
        return recordingRepository.findAll();
    }

    public Recording getById(long id) {
        return recordingRepository.getById(id);
    }

    @Transactional
    public Recording create(CreateRecordingRequest request) {
        var recording = convertRequestToRecording(request);
        return recordingRepository.save(recording);
    }

    public Recording update(long id, CreateRecordingRequest request) {
        if (!recordingRepository.existsById(id)) {
            throw new RuntimeException(String.format(NOT_FOUND, id));
        }
        var recording = convertRequestToRecording(id, request);
        return recordingRepository.save(recording);
    }

    public Recording edit(long id, CreateRecordingRequest request) {
        Optional<Recording> recording = recordingRepository.findById(id);
        if (recording.isEmpty()) {
            throw new RuntimeException(String.format(NOT_FOUND, id));
        }
        var newRecordingData = convertRequestToRecording(id, request);
        recording.get().copyNonNullProperties(newRecordingData);
        return recordingRepository.save(recording.get());
    }

    public void delete(long id) {
        if (!recordingRepository.existsById(id)) {
            throw new RuntimeException(String.format(NOT_FOUND, id));
        }
        recordingRepository.deleteById(id);
    }

    public List<Recording> getByReleaseTimeRange(ZonedDateTime start, ZonedDateTime end) {
        return recordingRepository.findAllByReleaseTimeBetween(start, end);
    }

    public List<Recording> getByCompanyName(String name) {
        return recordingRepository.findAllByCompanyName(name);
    }

    private Recording convertRequestToRecording(CreateRecordingRequest request) {
        return convertRequestToRecording(null, request);
    }

    private Recording convertRequestToRecording(Long id, CreateRecordingRequest request) {
        var recording = Recording.builder()
                .id(id)
                .title(request.getTitle())
                .version(request.getVersion())
                .releaseTime(request.getReleaseTime())
                .build();
        var singerDto = request.getSinger();
        if (singerDto != null) {
            var singer = singerRepository.findById(singerDto.getId()).orElseGet(() -> {
                var temp = new Singer();
                temp.setName(singerDto.getName());
                return singerRepository.save(temp);
            });
            recording.setSinger(singer);
        }
        return recording;
    }
}
