package com.sousadev.voteapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sousadev.voteapp.dtos.VoteDto;
import com.sousadev.voteapp.entity.Vote;
import com.sousadev.voteapp.service.VoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vote create(
            @RequestBody @Valid VoteDto request) {
        return voteService.create(request);
    }
}
