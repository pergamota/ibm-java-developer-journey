package com.example.demo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Anotacoes;

public interface NoteRepository extends MongoRepository<Anotacoes, String> {
    
}
