package com.junebird.junebird_app.domain.admin.client.client_document.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientDocumentRequestDto(

        @NotNull
        Long documentTypeId,

        @NotBlank @Size(max = 100)
        String document
) {
}
