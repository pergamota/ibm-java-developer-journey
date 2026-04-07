package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Anotacoes;
import com.example.demo.repository.NoteRepository;

@Service
public class NoteService {
    
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Anotacoes salvar(Anotacoes anotacoes) {
        return repository.save(anotacoes);
    }

    public List<Anotacoes> listar() {
        return repository.findAll();
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }

    public Anotacoes buscarPorId(String id) {
        return repository.findById(id).orElse(null);
    }

}
