package com.junebird.junebird_app.domain.document_type.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DocumentTypeRequestDto(

        @Size(max = 50)
        @NotBlank
        String code,

        @Size(max = 200)
        @NotBlank
        String description,

        @Size(max = 2)
        @NotBlank
        String countryCode
) {
}
