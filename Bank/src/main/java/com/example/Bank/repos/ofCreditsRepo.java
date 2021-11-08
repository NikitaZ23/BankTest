package com.example.Bank.repos;

import com.example.Bank.domain.Credits;
import com.example.Bank.domain.ofCredits;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ofCreditsRepo extends CrudRepository<ofCredits,Long> {
    ofCredits findById(UUID id);
}
