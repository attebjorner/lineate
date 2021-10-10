package com.example.offerdaysongs.dto.mapper;

import com.example.offerdaysongs.dto.CompanyDto;
import com.example.offerdaysongs.model.Company;

import java.util.stream.Collectors;

public class CompanyMapper
{
    public static CompanyDto toDto(Company company)
    {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getRecordings().stream()
                        .map(RecordingMapper::toDto)
                        .collect(Collectors.toList()),
                company.getRecordingPrice()
        );
    }
}
