package org.brisa.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String papel;

    public Funcionario() {
    }

    public Funcionario(String nome, String papel) {
        this.nome = nome;
        this.papel = papel;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
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