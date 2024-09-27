package org.brisa.controllers;

import org.brisa.domain.Funcionario;
import org.brisa.exceptions.FuncionarioNotFoundException;
import org.brisa.repositories.FuncionarioRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class FuncionarioController {

    private final FuncionarioRepository repository;

    private final FuncionarioModelAssembler assembler;

    FuncionarioController(FuncionarioRepository repository, FuncionarioModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @PostMapping("/funcionarios")
    ResponseEntity<Funcionario> novoFuncionario(@RequestBody Funcionario novoFuncionario) {
        EntityModel<Funcionario> entityModel = assembler.toModel(repository.save(novoFuncionario));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel.getContent());
    }

    @GetMapping("/funcionarios")
    CollectionModel<EntityModel<Funcionario>> all() {
        List<EntityModel<Funcionario>> funcionarios = repository.findAll().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(funcionarios,
                linkTo(methodOn(FuncionarioController.class).all()).withSelfRel());
    }

    @GetMapping("/funcionarios/{id}")
    EntityModel<Funcionario> one(@PathVariable UUID id) {
        var employee = repository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException(id));
        return assembler.toModel(employee);
    }

    @PutMapping("/funcionarios/{id}")
    Funcionario substituirFuncionario(@RequestBody Funcionario novoFuncionario, @PathVariable UUID id) {
        Funcionario updateFuncionario = repository.findById(id)
                .stream().map(funcionarioAtual -> {
                    funcionarioAtual.setNome(novoFuncionario.getNome());
                    funcionarioAtual.setSobrenome(novoFuncionario.getSobrenome());
                    funcionarioAtual.setPapel(novoFuncionario.getPapel());
                    return repository.save(funcionarioAtual);
                })
                .findFirst().orElseGet(() -> {
                    novoFuncionario.setId(id);
                    return repository.save(novoFuncionario);
                });
        EntityModel<Funcionario> entityModel = assembler.toModel(updateFuncionario);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel.getContent()).getBody();
    }

    @DeleteMapping("/funcionarios/{id}")
    ResponseEntity<Funcionario> deleteFuncionario(@PathVariable UUID id) {
        if (!repository.existsById(id)) {
            throw new FuncionarioNotFoundException(id);
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}