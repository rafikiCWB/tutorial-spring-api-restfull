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

    @Bean
    CommandLineRunner IniciarBancoDeDados(FuncionarioRepository repository) {
        return args -> {
            log.info("PreCarregamento" + repository.save(new Funcionario("Bilbo Baggins", "Burglar")));
            log.info("PreCarregamento" + repository.save(new Funcionario("Frodo Baggins", "thief")));
        };
    }

}