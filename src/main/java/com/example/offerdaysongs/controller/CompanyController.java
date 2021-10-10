package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CompanyDto;
import com.example.offerdaysongs.dto.mapper.CompanyMapper;
import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController
{
    private static final String ID = "id";
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public List<CompanyDto> getAll()
    {
        return companyService.getAll().stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id:[\\d]+}")
    public CompanyDto get(@PathVariable(ID) long id)
    {
        var company = companyService.getById(id);
        return CompanyMapper.toDto(company);
    }

    @PostMapping("/")
    public CompanyDto create(@RequestBody CreateCompanyRequest request)
    {
        return CompanyMapper.toDto(companyService.create(request));
    }

    @PutMapping("/{id:[\\d]+}")
    public CompanyDto update(@PathVariable(ID) long id,
                             @RequestBody CreateCompanyRequest request)
    {
        return CompanyMapper.toDto(companyService.update(id, request));
    }

    @PatchMapping("/{id:[\\d]+}")
    public CompanyDto edit(@PathVariable(ID) long id,
                           @RequestBody CreateCompanyRequest request)
    {
        return CompanyMapper.toDto(companyService.edit(id, request));
    }

    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable(ID) long id)
    {
        companyService.delete(id);
    }
}
