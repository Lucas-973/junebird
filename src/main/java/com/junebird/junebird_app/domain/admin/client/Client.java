package com.junebird.junebird_app.domain.admin.client;

import com.junebird.junebird_app.domain.admin.client.client_document.ClientDocument;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "client", schema = "admin")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registered_name", nullable = false)
    private String registeredName;

    @Column(name = "trade_name")
    private String tradeName;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientDocument> documents = new ArrayList<>();

    public void addDocument(ClientDocument document) {
        documents.add(document);
        document.setClient(this);
    }

    public void removeDocuments(Set<Long> documentIds) {
        documents.removeIf(document -> {
            if (!documentIds.contains(document.getId())) {
                return false;
            }

            document.setClient(null);
            return true;
        });
    }

}
