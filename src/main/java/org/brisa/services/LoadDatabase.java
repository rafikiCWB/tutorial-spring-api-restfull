package org.brisa.services;

import org.brisa.domain.Funcionario;
import org.brisa.repositories.FuncionarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private static final String SOBRENOME = "Baggins";
    private static final String MESSAGE = "PreCarregamento {}";

    @Bean
    CommandLineRunner iniciarBancoDeDados(FuncionarioRepository repository) {
        return args -> {
            if (repository.findByNomeAndSobrenome("Frodo", SOBRENOME) == null) {
                Funcionario frodo = new Funcionario("Frodo", SOBRENOME, "thief");
                Funcionario savedFrodo = repository.save(frodo);
                log.info(MESSAGE, savedFrodo);
            }
            if (repository.findByNomeAndSobrenome("Bilbo", SOBRENOME) == null) {
                Funcionario bilbo = new Funcionario("Bilbo", SOBRENOME, "Burglar");
                Funcionario savedBilbo = repository.save(bilbo);
                log.info(MESSAGE, savedBilbo);
            }
            if (repository.findByNomeAndSobrenome("Gandalf", "Mithrandir") == null) {
                Funcionario gandalf = new Funcionario("Gandalf", "Mithrandir", "Mago");
                Funcionario savedGandalf = repository.save(gandalf);
                log.info(MESSAGE, savedGandalf);
            }
        };
    }

}