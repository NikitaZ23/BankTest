package com.example.Bank.repos;

import ch.qos.logback.core.net.server.Client;
import com.example.Bank.domain.Clients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ClientsRepo extends CrudRepository<Clients,Long> {
    List<Clients> findByName(String name);
    Clients findById(UUID id);
}
