package com.junebird.junebird_app.domain.admin.client;

import com.junebird.junebird_app.domain.admin.client.client_document.ClientDocument;
import com.junebird.junebird_app.domain.admin.client.client_document.dto.ClientDocumentResponseDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientCreateRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientEditRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documents", ignore = true)
    Client toEntity(ClientCreateRequestDto dto);

    ClientResponseDto toResponseDto(Client client);

    ClientDocumentResponseDto toResponseDto(ClientDocument document);

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documents", ignore = true)
    void update(
            ClientEditRequestDto dto,
            @MappingTarget Client client
    );
}