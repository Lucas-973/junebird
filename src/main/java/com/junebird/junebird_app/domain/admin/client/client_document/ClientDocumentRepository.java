package com.junebird.junebird_app.domain.admin.client.client_document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDocumentRepository extends JpaRepository<ClientDocument, Long> {

    Optional<ClientDocument> findByIdAndClientId(Long id, Long clientId);
}
