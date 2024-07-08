package org.brisa.controllers;

import org.brisa.domain.Funcionario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FuncionarioModelAssembler implements RepresentationModelAssembler<Funcionario, EntityModel<Funcionario>> {

    @Override
    public EntityModel<Funcionario> toModel(Funcionario funcionario) {
        return EntityModel.of(funcionario,
                linkTo(methodOn(FuncionarioController.class).one(funcionario.getId())).withSelfRel(),
                linkTo(methodOn(FuncionarioController.class).all()).withRel("funcionarios"));
    }

}
