package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CompanyDto;
import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.dto.requests.CreateRecordingRequest;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recordings")
public class RecordingController {
    private static final String ID = "id";
    private final RecordingService recordingService;

    @Autowired
    public RecordingController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @GetMapping("/")
    public List<RecordingDto> getAll(){
        return recordingService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id:[\\d]+}")
    public RecordingDto get(@PathVariable(ID) long id) {
        var recording = recordingService.getById(id);
        return convertToDto(recording);
    }

    @PostMapping("/")
    public RecordingDto create(@RequestBody CreateRecordingRequest request) {
        return convertToDto(recordingService.create(request));
    }

    @PutMapping("/{id:[\\d]+}")
    public RecordingDto update(@PathVariable(ID) long id,
                               @RequestBody CreateRecordingRequest request) {
        return convertToDto(recordingService.update(id, request));
    }

    @PatchMapping("/{id:[\\d]+}")
    public RecordingDto edit(@PathVariable(ID) long id,
                             @RequestBody CreateRecordingRequest request) {
        return convertToDto(recordingService.edit(id, request));
    }

    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable(ID) long id) {
        recordingService.delete(id);
    }

    private RecordingDto convertToDto(Recording recording) {
        var singer = recording.getSinger();
        return new RecordingDto(recording.getId(),
                                recording.getTitle(),
                                recording.getVersion(),
                                recording.getReleaseTime(),
                                singer != null ? new SingerDto(singer.getId(), singer.getName()) : null);
    }
}
