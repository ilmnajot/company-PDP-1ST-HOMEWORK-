package com.company.company.service;
import com.company.company.entity.Worker;
import com.company.company.repository.WorkerRepository;
import com.company.company.result.ApiResponse;
import com.company.company.result.WorkerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    public List<Worker> getWorkers(){
        return workerRepository.findAll();
    }

    /**
     *
     * @param workerDto
     * @return
     */
    public ApiResponse addWorker(WorkerDto workerDto) {
        boolean phoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (phoneNumber){
            return new ApiResponse("this phone number is already existent, so please use another number", false);
        }
        Worker worker = new Worker();
        worker.setAddress(workerDto.getAddress());
        worker.setDepartment(workerDto.getDepartment());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("the data successfully saved", true);
    }

    /**
     *
     * @param id
     * @return
     */
    public Worker getWorkerById(Long id){
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isEmpty()){
            return null;
        }
        return workerRepository.getReferenceById(id);
    }
    public ApiResponse deleteWorkerById(Long id){
    workerRepository.deleteById(id);
    return new ApiResponse("the data deleted successfully", true);
    }

    /**
     *
     * @param id
     * @param workerDto
     * @return
     */
    public ApiResponse editWorker(Long id, WorkerDto workerDto){
        boolean numberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(id, workerDto.getPhoneNumber());
        if (numberAndIdNot){
            return new ApiResponse("this number is exist in the system, pls select another one", false);
        }
        Optional<Worker> byId = workerRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("this person is not exist here", false);
        }
        Worker worker = new Worker();
        worker.setAddress(workerDto.getAddress());
        worker.setDepartment(workerDto.getDepartment());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("updated", true);

    }
}
