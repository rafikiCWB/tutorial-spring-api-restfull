package org.brisa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String papel;

    public Funcionario() {
    }

    public Funcionario(String nome, String papel) {
        this.nome = nome;
        this.papel = papel;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Funcionario))
            return false;
        Funcionario funcionario = (Funcionario) o;
        return Objects.equals(this.id, funcionario.id) && Objects.equals(this.nome, funcionario.nome) && Objects.equals(this.papel, funcionario.papel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.papel);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.nome + '\'' + ", role='" + this.papel + '\'' + '}';
    }

}