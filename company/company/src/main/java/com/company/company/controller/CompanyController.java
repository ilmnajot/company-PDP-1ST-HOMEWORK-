package com.company.company.controller;
import com.company.company.result.ApiResponse;
import com.company.company.result.CompanyDto;
import com.company.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping("/all")
    public ResponseEntity<?> getCompanies(){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanyById(id));
    }
    @PostMapping
    public ResponseEntity addCompany(@Valid @RequestBody CompanyDto companyDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(companyDto));
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCompany(@PathVariable Long id){
        ApiResponse apiResponse = companyService.deleteById(id);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.editCompany(id, companyDto));
    }
}
