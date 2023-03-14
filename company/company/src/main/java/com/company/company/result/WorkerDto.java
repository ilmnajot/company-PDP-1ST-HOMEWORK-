package com.company.company.result;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @NotNull(message = "name cannot be blank, so please enter your name here ")
    private String name;
    @NotNull(message = "phone number cannot be blank, so please enter your name here ")
    private String  phoneNumber;
    @NotNull(message = "address cannot be blank, so please enter your name here ")
    private String address;
    @NotNull(message = "department cannot be blank, so please enter your name here ")
    private String department;
}
