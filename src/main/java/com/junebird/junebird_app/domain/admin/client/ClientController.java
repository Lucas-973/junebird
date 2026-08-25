package com.junebird.junebird_app.domain.admin.client;

import com.junebird.junebird_app.domain.admin.client.dto.ClientCreateRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientEditRequestDto;
import com.junebird.junebird_app.domain.admin.client.dto.ClientResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDto create(
            @Valid @RequestBody ClientCreateRequestDto request
    ) {
        return service.create(request);
    }

    @GetMapping
    public List<ClientResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientResponseDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public ClientResponseDto patch(
            @PathVariable Long id,
            @Valid @RequestBody ClientEditRequestDto request
            ) {
        return service.patch(id, request);
    }

}