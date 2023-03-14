package com.company.company.result;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "corpName cannot be blank, pls enter corpName!")
    private String corpName;

    @NotNull(message="director name cannot be blank, pls enter the director name")
    private String directorName;

    @NotNull(message="address cannot be blank")
    private String address;
}
