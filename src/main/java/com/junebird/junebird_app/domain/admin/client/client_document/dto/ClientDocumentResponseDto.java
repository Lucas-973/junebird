package com.junebird.junebird_app.domain.admin.client.client_document.dto;

import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeResponseDto;

public record ClientDocumentResponseDto(
        Long id,
        DocumentTypeResponseDto documentType,
        String document
) {
}
