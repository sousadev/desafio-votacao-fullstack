package com.sousadev.voteapp.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.Duration;

public record PautaDto(

        @NotBlank
        String name,

        String description,

        Duration expireIn

) {
}