package com.company.company.controller;

import com.company.company.entity.Address;
import com.company.company.result.AddressDto;
import com.company.company.result.ApiResponse;
import com.company.company.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAddresses() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddresses());
    }

    @PostMapping
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse addingAddress = addressService.saveAddress(addressDto);
        return ResponseEntity.status(addingAddress.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(addingAddress);

//    if (addingAddress.isSuccess()){
//        return ResponseEntity.status(HttpStatus.CREATED).body(addingAddress);
//    }
//    return ResponseEntity.status(HttpStatus.CONFLICT).body(addingAddress);}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return new ResponseEntity<Address>(addressService.getAddress(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto){
        return addressService.editAddress(id, addressDto);
    }

}

