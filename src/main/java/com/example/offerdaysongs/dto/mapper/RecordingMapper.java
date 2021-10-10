package com.example.offerdaysongs.dto.mapper;

import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.model.Recording;

public class RecordingMapper
{
    public static RecordingDto toDto(Recording recording)
    {
        var singer = recording.getSinger();
        return new RecordingDto(
                recording.getId(),
                recording.getTitle(),
                recording.getVersion(),
                recording.getReleaseTime(),
                singer != null ? SingerMapper.toDto(singer) : null
        );
    }
}
