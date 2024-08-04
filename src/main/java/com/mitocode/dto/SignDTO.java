package com.mitocode.dto;

import com.mitocode.model.Patient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignDTO {

    private Integer idSign;

    @NotNull
    private Patient patient;
    @NotNull
    private LocalDate date;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String temperature;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String pulse;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String respiratoryRhythm;
}
