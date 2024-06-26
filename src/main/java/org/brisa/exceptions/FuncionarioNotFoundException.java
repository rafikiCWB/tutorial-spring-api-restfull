package org.brisa.exceptions;

public class FuncionarioNotFoundException extends RuntimeException {

    public FuncionarioNotFoundException(Long id) {
        super("Não foi possível encontrar o funcionário com id " + id);
    }

}