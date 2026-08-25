package com.junebird.junebird_app.domain.admin.client.client_document.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientDocumentEditRequestDto(
        @NotNull
        Long id,

        Long documentTypeId,

        @Size(max = 100)
        @Pattern(regexp = "(?s).*\\S.*", message = "must not be blank")
        String document
) {
}
