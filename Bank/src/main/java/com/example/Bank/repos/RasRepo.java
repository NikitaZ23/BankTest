package com.example.Bank.repos;

import com.example.Bank.domain.Clients;
import com.example.Bank.domain.Ras;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface RasRepo extends CrudRepository<Ras,Long> {
    Iterable<Ras> findByidOfCr(UUID idOfCr);
}
