package com.example.Bank.repos;

import com.example.Bank.domain.Clients;
import com.example.Bank.domain.Credits;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditsRepo extends CrudRepository<Credits,Long> {
    Credits findById(UUID id);
}
