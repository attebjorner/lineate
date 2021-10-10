package com.example.offerdaysongs.dto.mapper;

import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.model.Singer;

public class SingerMapper
{
    public static SingerDto toDto(Singer singer)
    {
        return new SingerDto(singer.getId(), singer.getName());
    }
}
