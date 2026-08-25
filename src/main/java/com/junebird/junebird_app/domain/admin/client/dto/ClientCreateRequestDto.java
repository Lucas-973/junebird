package com.junebird.junebird_app.domain.admin.client.dto;

import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientCreateRequestDto(

        @Size(max = 200) @NotBlank String registeredName,

        @Size(max = 200) String tradeName,

        @NotEmpty
        @Valid
        List<ClientDocumentRequestDto> documents
) {
}
