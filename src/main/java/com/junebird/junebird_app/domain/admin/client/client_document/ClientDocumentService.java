package com.junebird.junebird_app.domain.admin.client.client_document;

import com.junebird.junebird_app.domain.admin.client.Client;
import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentChangesRequestDto;
import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentEditRequestDto;
import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentRequestDto;
import com.junebird.junebird_app.domain.document_type.DocumentType;
import com.junebird.junebird_app.domain.document_type.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientDocumentService {

    private final ClientDocumentRepository clientDocumentRepository;
    private final DocumentTypeRepository documentTypeRepository;

    public void addAll(
            Client client,
            List<ClientDocumentRequestDto> documents
    ) {
        documents.forEach(document -> add(client, document));
    }

    public void applyChanges(
            Client client,
            ClientDocumentChangesRequestDto changes
    ) {
        if (changes == null) {
            return;
        }

        if (changes.remove() != null) {
            changes.remove().forEach(documentId -> remove(client, documentId));
            clientDocumentRepository.flush();
        }

        if (changes.update() != null) {
            changes.update().forEach(document -> update(client.getId(), document));
        }

        if (changes.add() != null) {
            addAll(client, changes.add());
        }
    }

    private void add(Client client, ClientDocumentRequestDto dto) {
        DocumentType documentType = findDocumentType(dto.documentTypeId());

        ClientDocument document = new ClientDocument();
        document.setDocument(dto.document());
        document.setDocumentType(documentType);

        client.addDocument(document);
    }

    private void update(Long clientId, ClientDocumentEditRequestDto dto) {
        ClientDocument document = findClientDocument(clientId, dto.id());

        if (dto.documentTypeId() != null) {
            document.setDocumentType(findDocumentType(dto.documentTypeId()));
        }

        if (dto.document() != null) {
            document.setDocument(dto.document());
        }
    }

    private void remove(Client client, Long documentId) {
        ClientDocument document = findClientDocument(client.getId(), documentId);
        client.removeDocument(document);
    }

    private ClientDocument findClientDocument(Long clientId, Long documentId) {
        return clientDocumentRepository
                .findByIdAndClientId(documentId, clientId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Client document not found: " + documentId
                ));
    }

    private DocumentType findDocumentType(Long documentTypeId) {
        return documentTypeRepository
                .findById(documentTypeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Document type not found: " + documentTypeId
                ));
    }
}
