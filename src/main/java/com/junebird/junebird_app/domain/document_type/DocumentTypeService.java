package com.junebird.junebird_app.domain.document_type;

import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeRequestDto;
import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    public DocumentTypeResponseDto create(DocumentTypeRequestDto dto) {
        DocumentType documentType = documentTypeMapper.toEntity(dto);
        DocumentType savedDocumentType = documentTypeRepository.save(documentType);

        return documentTypeMapper.toResponseDto(savedDocumentType);

    }

}
