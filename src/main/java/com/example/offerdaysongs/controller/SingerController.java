package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.dto.requests.CreateSingerRequest;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/singers")
public class SingerController {
    private static final String ID = "id";
    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService)
    {
        this.singerService = singerService;
    }

    @GetMapping("/{id:[\\d]+}")
    public SingerDto get(@PathVariable(ID) long id) {
        var singer = singerService.getById(id);
        return convertToDto(singer);
    }

    @GetMapping("/")
    public List<SingerDto> getAll() {
        var singers = singerService.getAllSingers();
        return singers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public SingerDto create(@RequestBody CreateSingerRequest request) {
        return convertToDto(singerService.create(request));
    }

    @PutMapping("/{id:[\\d]+}")
    public SingerDto update(@PathVariable(ID) long id,
                            @RequestBody CreateSingerRequest request) {
        return convertToDto(singerService.update(id, request));
    }

    @PatchMapping("/{id:[\\d]+}")
    public SingerDto edit(@PathVariable(ID) long id,
                          @RequestBody CreateSingerRequest request) {
        return convertToDto(singerService.edit(id, request));
    }

    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable(ID) long id) {
        singerService.delete(id);
    }

    private SingerDto convertToDto(Singer singer) {
        return new SingerDto(singer.getId(), singer.getName());
    }
}
