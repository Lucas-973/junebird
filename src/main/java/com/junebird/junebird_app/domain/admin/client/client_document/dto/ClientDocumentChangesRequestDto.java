package com.junebird.junebird_app.domain.admin.client.client_document.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClientDocumentChangesRequestDto(
        List<@NotNull @Valid ClientDocumentRequestDto> add,

        List<@NotNull @Valid ClientDocumentEditRequestDto> update,

        List<@NotNull Long> remove
) {
}
