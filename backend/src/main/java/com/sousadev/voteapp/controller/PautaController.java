package com.sousadev.voteapp.controller;

import com.sousadev.voteapp.dtos.PautaDto;
import com.sousadev.voteapp.dtos.PautaResponseDto;
import com.sousadev.voteapp.service.PautaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaResponseDto create(
            @RequestBody @Valid PautaDto request
    ) {
        return pautaService.create(request);
    }

    @GetMapping
    public List<PautaResponseDto> findAll() {
        return pautaService.findAll();
    }

    @GetMapping("/{id}")
    public PautaResponseDto findById(
            @PathVariable UUID id
    ) {
        return pautaService.findById(id);
    }
}