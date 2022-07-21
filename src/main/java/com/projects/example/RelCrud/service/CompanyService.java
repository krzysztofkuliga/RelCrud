package com.projects.example.RelCrud.service;

import com.projects.example.RelCrud.CompanyRepository;
import com.projects.example.RelCrud.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;


    public List<Company> getAllCompanies(String name) {
        if (name == null) {
            return repository.findAll();
        } else {
            return repository.findByNameContaining(name);
        }
    }

    public Optional<Company> getById(Long id) {
        return repository.findById(id);
    }

    public Company saveCompany(Company saved) {
        return new Company(saved.getName(),saved.getDescription(),false);
    }

    public Company updateCompany(Long id, Company company) {
        Optional<Company> found = getById(id);
        if (found.isPresent()) {
            Company companyToUpdate = found.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setPublished(company.isPublished());
            return saveCompany(companyToUpdate);
        }
        return null;
    }

    public void deleteCompany(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllCompanies() {
        repository.deleteAll();
    }

    public List<Company> findByPublished() {
        return repository.findByPublished(true);
    }
}
