package org.brisa.exceptions;

import java.util.UUID;

public class FuncionarioNotFoundException extends RuntimeException {

    public FuncionarioNotFoundException(UUID id) {
        super("Não foi possível encontrar o funcionário com id " + id);
    }

}