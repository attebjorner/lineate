package com.example.offerdaysongs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyDto
{
    long id;
    String name;
    List<RecordingDto> recordings;
    int recordingPrice;
}
