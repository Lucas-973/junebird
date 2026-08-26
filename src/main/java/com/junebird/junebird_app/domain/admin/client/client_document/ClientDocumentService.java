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
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Map<Long, ClientDocument> documentsById = client.getDocuments()
                .stream()
                .collect(Collectors.toMap(ClientDocument::getId, Function.identity()));

        if (changes.remove() != null) {
            Set<Long> documentIds = Set.copyOf(changes.remove());
            documentIds.forEach(documentId ->
                    findClientDocument(client, documentsById, documentId)
            );

            client.removeDocuments(documentIds);
            clientDocumentRepository.flush();
        }

        if (changes.update() != null) {
            changes.update().forEach(document ->
                    update(client, documentsById, document)
            );
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

    private void update(
            Client client,
            Map<Long, ClientDocument> documentsById,
            ClientDocumentEditRequestDto dto
    ) {
        ClientDocument document = findClientDocument(
                client,
                documentsById,
                dto.id()
        );

        if (dto.documentTypeId() != null) {
            document.setDocumentType(findDocumentType(dto.documentTypeId()));
        }

        if (dto.document() != null) {
            document.setDocument(dto.document());
        }
    }

    private ClientDocument findClientDocument(
            Client client,
            Map<Long, ClientDocument> documentsById,
            Long documentId
    ) {
        ClientDocument document = documentsById.get(documentId);

        if (document == null || document.getClient() != client) {
            throw new IllegalArgumentException(
                    "Client document not found: " + documentId
            );
        }

        return document;
    }

    private DocumentType findDocumentType(Long documentTypeId) {
        return documentTypeRepository
                .findById(documentTypeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Document type not found: " + documentTypeId
                ));
    }
}
