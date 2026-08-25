package com.junebird.junebird_app.domain.admin.client.client_document;

import com.junebird.junebird_app.domain.admin.client.Client;
import com.junebird.junebird_app.domain.document_type.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "client_document",
        schema = "admin",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"client_id", "document_type_id", "document"}
                )
        }
)
public class ClientDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

    @Column(name = "document", nullable = false, length = 100)
    private String document;
}