package com.company.company.controller;
import com.company.company.entity.Department;
import com.company.company.result.ApiResponse;
import com.company.company.result.DepartmentDto;
import com.company.company.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id){
      return ResponseEntity.status(HttpStatus.OK).body(departmentService.getById(id));
    }
    @PostMapping
    public ApiResponse addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        return departmentService.saveDepartment(departmentDto);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
