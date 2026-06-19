package com.sousadev.voteapp.service;

import com.sousadev.voteapp.dtos.PautaDto;
import com.sousadev.voteapp.dtos.PautaResponseDto;
import com.sousadev.voteapp.entity.Pauta;
import com.sousadev.voteapp.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository repository;

    public PautaResponseDto create(PautaDto request) {
        Pauta pauta = Pauta.builder()
                .name(request.name())
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .expireIn(request.expireIn())
                .build();

        Pauta savedPauta = repository.save(pauta);
        return PautaResponseDto.fromEntity(savedPauta);
    }

    public List<PautaResponseDto> findAll() {
        return repository.findAll().stream()
                .map(PautaResponseDto::fromEntity)
                .toList();
    }

    public PautaResponseDto findById(UUID id) {
        Pauta pauta = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));
        return PautaResponseDto.fromEntity(pauta);
    }
}