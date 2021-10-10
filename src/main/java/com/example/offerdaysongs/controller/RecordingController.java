package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.mapper.RecordingMapper;
import com.example.offerdaysongs.dto.requests.CreateRecordingRequest;
import com.example.offerdaysongs.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.format.annotation.DateTimeFormat.ISO;

@RestController
@RequestMapping("/api/recordings")
public class RecordingController
{
    private static final String ID = "id";
    private final RecordingService recordingService;

    @Autowired
    public RecordingController(RecordingService recordingService)
    {
        this.recordingService = recordingService;
    }

    @GetMapping("/")
    public List<RecordingDto> getAll()
    {
        return recordingService.getAll().stream()
                .map(RecordingMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id:[\\d]+}")
    public RecordingDto get(@PathVariable(ID) long id)
    {
        var recording = recordingService.getById(id);
        return RecordingMapper.toDto(recording);
    }

    @PostMapping("/")
    public RecordingDto create(@RequestBody CreateRecordingRequest request)
    {
        return RecordingMapper.toDto(recordingService.create(request));
    }

    @PutMapping("/{id:[\\d]+}")
    public RecordingDto update(@PathVariable(ID) long id,
                               @RequestBody CreateRecordingRequest request)
    {
        return RecordingMapper.toDto(recordingService.update(id, request));
    }

    @PatchMapping("/{id:[\\d]+}")
    public RecordingDto edit(@PathVariable(ID) long id,
                             @RequestBody CreateRecordingRequest request)
    {
        return RecordingMapper.toDto(recordingService.edit(id, request));
    }

    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable(ID) long id)
    {
        recordingService.delete(id);
    }

    @GetMapping("/release_time")
    public List<RecordingDto> getByReleaseTime(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) ZonedDateTime start,
                                               @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) ZonedDateTime end)
    {
        return recordingService.getByReleaseTimeRange(start, end).stream()
                .map(RecordingMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/company")
    public List<RecordingDto> getByCompanyName(@RequestParam("name") String name)
    {
        return recordingService.getByCompanyName(name).stream()
                .map(RecordingMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/price/{id:[\\d]+}")
    public ResponseEntity<Map<String, Number>> getPrice(@PathVariable(ID) long id)
    {
        return ResponseEntity.ok(
                Map.of(
                        "id", id,
                        "price", recordingService.getRecordingPrice(id)
                )
        );
    }
}
