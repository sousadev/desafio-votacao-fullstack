package com.sousadev.voteapp.service;

import com.sousadev.voteapp.dtos.VoteDto;
import com.sousadev.voteapp.entity.Associate;
import com.sousadev.voteapp.entity.Pauta;
import com.sousadev.voteapp.entity.Vote;
import com.sousadev.voteapp.repository.AssociateRepository;
import com.sousadev.voteapp.repository.PautaRepository;
import com.sousadev.voteapp.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final AssociateRepository associateRepository;
    private final PautaRepository pautaRepository;

    public Vote create(VoteDto request) {
        Associate associate = associateRepository.findById(request.associateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado não encontrado"));

        Pauta pauta = pautaRepository.findById(request.pautaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada"));

        // Check if voting period is still valid
        LocalDateTime now = LocalDateTime.now();
        Duration expireIn = pauta.getExpireIn();
        if (expireIn == null) {
            expireIn = Duration.ofMinutes(36000); // Default to 1 minute if not set
        }
        LocalDateTime expirationTime = pauta.getCreatedAt().plus(expireIn);
        if (now.isAfter(expirationTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Votação encerrada para esta pauta");
        }

        if (voteRepository.findByAssociateIdAndPautaId(request.associateId(), request.pautaId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Associado já votou nesta pauta");
        }

        Vote vote = Vote.builder()
                .associate(associate)
                .pauta(pauta)
                .value(request.value())
                .build();

        return voteRepository.save(vote);
    }
}
