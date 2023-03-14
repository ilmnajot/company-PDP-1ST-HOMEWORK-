package com.company.company.service;

import com.company.company.entity.Company;
import com.company.company.repository.CompanyRepository;
import com.company.company.result.ApiResponse;
import com.company.company.result.CompanyDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getCompanies(){
        return companyRepository.findAll();

    }
    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()){
            return companyRepository.getReferenceById(id);
        }
        return null ;
    }
    public ApiResponse saveCompany(CompanyDto companyDto){
        boolean byName = companyRepository.existsByCorpName(companyDto.getCorpName());

        if (byName){
            return new ApiResponse("this name is not aviable", false);
        }
        Company company = new Company();
        company.setAddress(companyDto.getAddress());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("data saved!", true);
    }
    public ApiResponse deleteById(Long id){
        companyRepository.deleteById(id);
        return new ApiResponse("the data deleted", true);
    }
    public ApiResponse editCompany(Long id, CompanyDto companyDto){
        boolean nameAndIdNot = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);
        if (nameAndIdNot){
            return new ApiResponse("this name is taken already", false);
        }
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("the comapny taken by id is not aviable here", false);
        }
        Company company = new Company();
        company.setAddress(companyDto.getAddress());
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("the company saved successfully", true);
    }
}
