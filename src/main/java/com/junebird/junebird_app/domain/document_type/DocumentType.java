package com.junebird.junebird_app.domain.document_type;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "document_type", schema = "public")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "code", nullable = false)
    public String code;

    @Column(name = "description", nullable = false)
    public String description;

    @Column(name = "country_code", nullable = false)
    public String countryCode;
}
