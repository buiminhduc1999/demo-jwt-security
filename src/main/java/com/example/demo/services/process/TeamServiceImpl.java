package com.example.demo.services.process;

import com.example.demo.repositories.TeamRepository;
import com.example.demo.services.process.impls.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Set<Integer> getIdLeader() {
        return teamRepository.findIdLeader();
    }

    @Override
    public Set<Integer> getIdMemberByIdLeader(int idLeader) {
        return teamRepository.findIdMemberByIdLeader(idLeader);
    }
}
