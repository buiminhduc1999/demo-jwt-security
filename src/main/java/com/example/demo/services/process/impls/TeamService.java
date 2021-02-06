package com.example.demo.services.process.impls;

import java.util.List;
import java.util.Set;

public interface TeamService {

    Set<Integer> getIdLeader();

    Set<Integer> getIdMemberByIdLeader(int idLeader);
}
