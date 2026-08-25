package com.junebird.junebird_app.domain.document_type;

import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeRequestDto;
import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentTypeMapper {

    @Mapping(target="id",ignore = true)
    DocumentType toEntity(DocumentTypeRequestDto dto);

    DocumentTypeResponseDto toResponseDto(DocumentType documentType);

}
