package com.company.company.service;
import com.company.company.entity.Address;
import com.company.company.repository.AddressRepository;
import com.company.company.result.AddressDto;
import com.company.company.result.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }
    public ApiResponse saveAddress(AddressDto addressDto){
        boolean save = addressRepository.existsByHomeNumber(addressDto.getHomeNumber());
        if (save){
            return new ApiResponse("your home number is existent already!!!", false);
        }
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("your address saved!", true);
    }
    public Address getAddress(Long id){
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent()){
            return addressRepository.getReferenceById(id);
        }
        return null;
    }
    public ApiResponse deleteAddress(Long id) {
 return new ApiResponse("data removed", true);
}

    public ApiResponse editAddress(Long id, AddressDto addressDto){
        boolean numberAndIdNot = addressRepository.existsByHomeNumberAndIdNot(id, addressDto.getHomeNumber());
        if (numberAndIdNot){
            return new ApiResponse("this number is already registered", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new ApiResponse("this number is not existent", false);
        }
        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ApiResponse("the data updated ", true);
    }
    }
