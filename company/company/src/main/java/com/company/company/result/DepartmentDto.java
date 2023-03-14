package com.company.company.result;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotNull(message="name cannot be blank")
    private String name;
    @NotNull(message="company cannot be blank")
    private String company;
}
