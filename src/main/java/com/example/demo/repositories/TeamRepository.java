package com.example.demo.repositories;

import com.example.demo.models.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Set;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

    @Query("SELECT t.idLeader FROM TeamEntity t")
    Set<Integer> findIdLeader();

    @Query("SELECT t.idMember FROM TeamEntity t WHERE t.idLeader = ?1")
    Set<Integer> findIdMemberByIdLeader(int idLeader);
}
