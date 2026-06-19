package com.sousadev.voteapp.dtos;

import com.sousadev.voteapp.enums.VoteValue;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VoteDto(

        @NotNull
        UUID associateId,

        @NotNull
        UUID pautaId,

        @NotNull
        VoteValue value
) {
}
