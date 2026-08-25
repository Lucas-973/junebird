package com.junebird.junebird_app.domain.admin.client;

import com.junebird.junebird_app.domain.admin.client.client_document.ClientDocumentService;
import com.junebird.junebird_app.domain.admin.client.dto.ClientCreateRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientEditRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDocumentService clientDocumentService;
    private final ClientMapper mapper;


    @Transactional
    public ClientResponseDto create(ClientCreateRequestDto dto) {
        Client client = mapper.toEntity(dto);
        clientDocumentService.addAll(client, dto.documents());

        Client savedClient = clientRepository.save(client);
        return mapper.toResponseDto(savedClient);
    }

    public List<ClientResponseDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    public ClientResponseDto findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return mapper.toResponseDto(client);
    }
    
    @Transactional
    public ClientResponseDto patch(Long id, ClientEditRequestDto dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        mapper.update(dto, client);
        clientDocumentService.applyChanges(client, dto.documentChanges());

        clientRepository.flush();
        return mapper.toResponseDto(client);
    }
}
