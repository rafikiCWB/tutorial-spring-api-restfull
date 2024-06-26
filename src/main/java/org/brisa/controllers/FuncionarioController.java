package org.brisa.controllers;

import org.brisa.domain.Funcionario;
import org.brisa.exceptions.FuncionarioNotFoundException;
import org.brisa.repositories.FuncionarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuncionarioController {

    private final FuncionarioRepository repository;

    FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }


    @PostMapping("/funcionarios")
    Funcionario novoFuncionario(@RequestBody Funcionario novoFuncionario) {
        return repository.save(novoFuncionario);
    }

    @GetMapping("/funcionarios")
    List<Funcionario> all() {
        return repository.findAll();
    }

    @GetMapping("/funcionarios/{id}")
    Funcionario one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException(id));
    }

    @PutMapping("/funcionarios/{id}")
    Funcionario substituirFuncionario(@RequestBody Funcionario novoFuncionario, @PathVariable Long id) {
        return repository.findById(id)
                .stream().map(funcionarioAtual -> {
                    funcionarioAtual.setNome(novoFuncionario.getNome());
                    funcionarioAtual.setPapel(novoFuncionario.getPapel());
                    return repository.save(funcionarioAtual);
                })
                .findFirst().orElseGet(() -> {
                    novoFuncionario.setId(id);
                    return repository.save(novoFuncionario);
                });
    }

    @DeleteMapping("/funcionarios/{id}")
    void deleteFuncionario(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new FuncionarioNotFoundException(id);
        }
        repository.deleteById(id);
    }

}