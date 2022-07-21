package com.projects.example.RelCrud.controller;

import com.projects.example.RelCrud.CompanyRepository;
import com.projects.example.RelCrud.model.Company;
import com.projects.example.RelCrud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyRepository repository;

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompaniesContainingName(@RequestParam(required = false) String name) {
        try {
            List<Company> companies = new ArrayList<>();
            if (name == null) {
                repository.findAll().forEach(companies::add);
            } else {
                repository.findByNameContaining(name).forEach(companies::add);
            }
            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(companies,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") long id) {
        Optional<Company> companyData = repository.findById(id);
        if (companyData.isPresent()) {
            return new ResponseEntity<>(companyData.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/companies")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        try {
            Company saveComp = repository.save(new Company(company.getName(),company.getDescription(),false));
            return new ResponseEntity<>(saveComp,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company updateCompany) {
        Optional<Company> optionalCompData = repository.findById(id);
        if (optionalCompData.isPresent()) {
            Company companyData = optionalCompData.get();
            companyData.setPublished(updateCompany.isPublished());
            companyData.setDescription(updateCompany.getDescription());
            companyData.setName(updateCompany.getName());
            return new ResponseEntity<>(repository.save(companyData),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/companies/{id}")
    public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/companies")
    public ResponseEntity<HttpStatus> deleteAllCompanies() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("companies/published")
    public ResponseEntity<List<Company>> findByPublished() {
        try {
            List<Company> published = repository.findByPublished(true);
            if (published.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(published,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
