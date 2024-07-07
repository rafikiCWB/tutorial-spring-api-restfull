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
    CommandLineRunner iniciarBancoDeDados(FuncionarioRepository repository) {
        return args -> {
            if (repository.findByNome("Frodo Baggins") == null) {
                Funcionario frodo = new Funcionario("Frodo Baggins", "thief");
                Funcionario savedFrodo = repository.save(frodo);
                log.info("PreCarregamento {}", savedFrodo);
            }
            if (repository.findByNome("Bilbo Baggins") == null) {
                Funcionario bilbo = new Funcionario("Bilbo Baggins", "Burglar");
                Funcionario savedBilbo = repository.save(bilbo);
                log.info("PreCarregamento {}", savedBilbo);
            }
        };
    }

}