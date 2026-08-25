package com.junebird.junebird_app.domain.document_type.dto;

public record DocumentTypeResponseDto(
        Long id,
        String code,
        String description,
        String countryCode
) {
}
