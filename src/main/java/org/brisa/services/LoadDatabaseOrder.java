package org.brisa.services;

import org.brisa.domain.Order;
import org.brisa.domain.Status;
import org.brisa.repositories.FuncionarioRepository;
import org.brisa.repositories.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabaseOrder {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabaseOrder.class);
    private static final String MESSAGE = "Preloaded ";

    @Bean
    CommandLineRunner initDatabase(FuncionarioRepository funcionarioRepository, OrderRepository orderRepository) {
        return args -> {
//            funcionarioRepository.save(new Funcionario("Bilbo", "Baggins", "burglar"));
//            funcionarioRepository.save(new Funcionario("Frodo", "Baggins", "thief"));
//            funcionarioRepository.findAll().forEach(funcionario -> log.info(MESSAGE +
//                    funcionario));

            orderRepository.save(new Order("MackBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("Iphone", Status.IN_PROGRESS));
            orderRepository.findAll().forEach(order -> {
                log.info(MESSAGE + order);
            });
        };

    }

}