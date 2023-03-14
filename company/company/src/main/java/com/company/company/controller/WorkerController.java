package com.company.company.controller;

import com.company.company.entity.Worker;
import com.company.company.result.ApiResponse;
import com.company.company.result.WorkerDto;
import com.company.company.service.WorkerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
@AllArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    @GetMapping("/all")
    public ResponseEntity<List<Worker>> getALlWorkers(){
        return new ResponseEntity<List<Worker>>(workerService.getWorkers(),HttpStatus.OK);
    }
    @PostMapping
    public ApiResponse saveWorker(@Valid @RequestBody WorkerDto workerDto){
        return workerService.addWorker(workerDto);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Worker> getWorker(@PathVariable Long id){
        return new ResponseEntity<Worker>(workerService.getWorkerById(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteWorker(@PathVariable Long id){
        ApiResponse apiResponse = workerService.deleteWorkerById(id);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorker(@PathVariable Long id, @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);


    }

}
