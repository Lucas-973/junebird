package com.junebird.junebird_app.domain.admin.client.dto;

import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentChangesRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

public record ClientEditRequestDto(
        @Size(max = 200)
        String registeredName,

        @Size(max = 200)
        String tradeName,

        @Valid
        ClientDocumentChangesRequestDto documentChanges
) {
}
