package com.example.offerdaysongs.service;

import com.example.offerdaysongs.dto.requests.CreateCompanyRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final static String NOT_FOUND = "Company with id %d not found";

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll()
    {
        return companyRepository.findAll();
    }

    public Company getById(long id)
    {
        return companyRepository.getById(id);
    }

    public Company create(CreateCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        return companyRepository.save(company);
    }

    public Company update(long id, CreateCompanyRequest request) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException(String.format(NOT_FOUND, id));
        }
        var company = new Company();
        company.setId(id);
        company.setName(request.getName());
        return companyRepository.save(company);
    }

    public Company edit(long id, CreateCompanyRequest request) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new RuntimeException(String.format(NOT_FOUND, id));
        }
        var newCompanyData = new Company();
        newCompanyData.setId(id);
        newCompanyData.setName(request.getName());
        company.get().copyNonNullProperties(newCompanyData);
        return companyRepository.save(company.get());
    }

    public void delete(long id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException(String.format(NOT_FOUND, id));
        }
        companyRepository.deleteById(id);
    }
}
