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
    private String sobrenome;
    private String papel;

    public Funcionario() {
    }

    public Funcionario(String nome, String sobrenome, String papel) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.papel = papel;
    }

    public String getFullname() {
        return this.nome + " " + this.sobrenome;
    }

    public void setFullname(String nome) {
        String[] parts = nome.split(" ");
        this.nome = parts[0];
        this.sobrenome = parts[1];
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
        return Objects.equals(this.id, funcionario.id)
                && Objects.equals(this.nome, funcionario.nome)
                && Objects.equals(this.sobrenome, funcionario.sobrenome)
                && Objects.equals(this.papel, funcionario.papel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nome, this.sobrenome, this.papel);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", firstName='" + this.nome + '\'' + ", lastName='" + this.sobrenome
                + '\'' + ", role='" + this.papel + '\'' + '}';
    }

}