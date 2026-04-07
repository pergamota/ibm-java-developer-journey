package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Anotacoes;
import com.example.demo.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @PostMapping
    public Anotacoes criar(@RequestBody Anotacoes note) {
        return service.salvar(note);
    }

    @GetMapping
    public List<Anotacoes> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Anotacoes buscarPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Anotacoes editar(@PathVariable String id, @RequestBody Anotacoes note) {
        note.setId(id);
        return service.salvar(note);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}