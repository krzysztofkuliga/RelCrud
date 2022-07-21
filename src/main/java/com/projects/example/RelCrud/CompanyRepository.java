package com.projects.example.RelCrud;

import com.projects.example.RelCrud.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    List<Company> findByPublished(boolean published);
    List<Company> findByNameContaining(String name);
}
