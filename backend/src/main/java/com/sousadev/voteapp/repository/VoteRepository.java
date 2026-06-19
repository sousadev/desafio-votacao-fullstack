package com.sousadev.voteapp.repository;

import com.sousadev.voteapp.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {

    Optional<Vote> findByAssociateIdAndPautaId(UUID associateId, UUID pautaId);
}
