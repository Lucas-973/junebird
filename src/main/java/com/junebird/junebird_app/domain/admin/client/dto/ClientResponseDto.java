package com.junebird.junebird_app.domain.admin.client.dto;

import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentResponseDto;

import java.util.List;

public record ClientResponseDto(
        Long id,
        String registeredName,
        String tradeName,
        List<ClientDocumentResponseDto> documents
) {
}
