package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CompanyDto;
import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.SingerDto;
import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Singer;
import com.example.offerdaysongs.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private static final String ID = "id";
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public List<CompanyDto> getAll() {
        return companyService.getAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id:[\\d]+}")
    public CompanyDto get(@PathVariable(ID) long id) {
        var company = companyService.getById(id);
        return convertToDto(company);
    }

    @PostMapping("/")
    public CompanyDto create(@RequestBody CreateCompanyRequest request) {
        return convertToDto(companyService.create(request));
    }

    @PutMapping("/{id:[\\d]+}")
    public CompanyDto update(@PathVariable(ID) long id,
                             @RequestBody CreateCompanyRequest request) {
        return convertToDto(companyService.update(id, request));
    }

    @PatchMapping("/{id:[\\d]+}")
    public CompanyDto edit(@PathVariable(ID) long id,
                           @RequestBody CreateCompanyRequest request) {
        return convertToDto(companyService.edit(id, request));
    }

    @DeleteMapping("/{id:[\\d]+}")
    public void delete(@PathVariable(ID) long id) {
        companyService.delete(id);
    }

    private CompanyDto convertToDto(Company company){
        return new CompanyDto(company.getId(), company.getName());
    }

}
