package com.junebird.junebird_app.domain.document_type;

import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeRequestDto;
import com.junebird.junebird_app.domain.document_type.dto.DocumentTypeResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DocumentTypeControllerTest {

    private DocumentTypeService service;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        service = mock(DocumentTypeService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new DocumentTypeController(service))
                .build();
    }

    @Test
    void createReturnsCreatedDocumentType() throws Exception {
        DocumentTypeRequestDto request = new DocumentTypeRequestDto("CPF", "Cadastro de Pessoas Fisicas", "BR");
        DocumentTypeResponseDto response = new DocumentTypeResponseDto(
                1L,
                request.code(),
                request.description(),
                request.countryCode()
        );

        when(service.create(request)).thenReturn(response);

        mockMvc.perform(post("/api/public/document-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "code": "CPF",
                                  "description": "Cadastro de Pessoas Físicas",
                                  "countryCode": "BR"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("CPF"))
                .andExpect(jsonPath("$.countryCode").value("BR"));

        verify(service).create(request);
    }
}
