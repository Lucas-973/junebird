package com.junebird.junebird_app.domain.admin.client;

import com.junebird.junebird_app.domain.admin.client.client_document.ClientDocument;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public void removeDocument(ClientDocument document) {
        documents.remove(document);
        document.setClient(null);
    }

}
