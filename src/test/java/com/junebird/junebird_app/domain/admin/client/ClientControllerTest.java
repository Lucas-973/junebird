package com.junebird.junebird_app.domain.admin.client;

import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientCreateRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientEditRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest {

    private ClientService service;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        service = mock(ClientService.class);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ClientController(service))
                .build();
    }

    @Test
    void createReturnsCreatedClient() throws Exception {
        ClientCreateRequestDto request = new ClientCreateRequestDto(
                "Junebird Ltda",
                "Junebird",
                List.of(new ClientDocumentRequestDto(1L, "123456789"))
        );

        when(service.create(request)).thenReturn(clientResponse());

        mockMvc.perform(post("/api/admin/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "registeredName": "Junebird Ltda",
                                  "tradeName": "Junebird",
                                  "documents": [
                                    { "documentTypeId": 1, "document": "123456789" }
                                  ]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.registeredName").value("Junebird Ltda"));

        verify(service).create(request);
    }

    @Test
    void findAllReturnsClients() throws Exception {
        when(service.findAll()).thenReturn(List.of(clientResponse()));

        mockMvc.perform(get("/api/admin/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(7))
                .andExpect(jsonPath("$[0].tradeName").value("Junebird"));

        verify(service).findAll();
    }

    @Test
    void findByIdReturnsClient() throws Exception {
        when(service.findById(7L)).thenReturn(clientResponse());

        mockMvc.perform(get("/api/admin/clients/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.registeredName").value("Junebird Ltda"));

        verify(service).findById(7L);
    }

    @Test
    void patchReturnsUpdatedClient() throws Exception {
        ClientEditRequestDto request = new ClientEditRequestDto("Junebird S.A.", null, null);
        ClientResponseDto response = new ClientResponseDto(7L, "Junebird S.A.", "Junebird", List.of());

        when(service.patch(7L, request)).thenReturn(response);

        mockMvc.perform(patch("/api/admin/clients/7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                { "registeredName": "Junebird S.A." }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.registeredName").value("Junebird S.A."));

        verify(service).patch(7L, request);
    }

    private ClientResponseDto clientResponse() {
        return new ClientResponseDto(7L, "Junebird Ltda", "Junebird", List.of());
    }
}
