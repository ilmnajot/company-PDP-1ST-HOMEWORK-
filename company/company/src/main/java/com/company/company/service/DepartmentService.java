package com.company.company.service;

import com.company.company.entity.Department;
import com.company.company.repository.DepartmentRepository;
import com.company.company.result.ApiResponse;
import com.company.company.result.DepartmentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }
    public Department getById(Long id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            return departmentRepository.getReferenceById(id);
        }
        return null;
    }
    public ApiResponse saveDepartment(DepartmentDto departmentDto){
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        if (existsByName){
            return new ApiResponse("this name is already taken", false);
        }
        Department department = new Department();
        department.setCompany(departmentDto.getCompany());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("saved!", true);
    }
    public ApiResponse deleteDepartment(Long id){
        departmentRepository.deleteById(id);
        return new ApiResponse("the data deleted", true);
    }
    public ApiResponse editDepartment(Long id, DepartmentDto departmentDto){
        boolean byNameAndIdNot = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id);
        if (byNameAndIdNot){
            return new ApiResponse("this name is already is taken", false);
        }
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("this name is not here", false);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(departmentDto.getCompany());
        departmentRepository.save(department);
        return new ApiResponse("saved seccssefully", true);
    }


}
