package com.junebird.junebird_app.domain.document_type;

import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeRequestDto;
import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/document-types")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentTypeResponseDto create(
            @Valid @RequestBody DocumentTypeRequestDto request
            ) {
        return service.create(request);
    }

}
