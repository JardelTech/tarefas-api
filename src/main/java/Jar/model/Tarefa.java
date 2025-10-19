package Jar.model; // Ou 'Jar.model' se você manteve o 'J' maiúsculo

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat; // <-- IMPORT ADICIONADO

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Tarefa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nome; 
    
    @JsonFormat(pattern = "dd/MM/yyyy") // <-- ANOTAÇÃO ADICIONADA AQUI
    private LocalDate dataEntrega; 
    
    private String responsavel; 

    // Construtor padrão
    public Tarefa() {
    }

    // --- Getters e Setters ---
    
    public Long getId() {
        return id;
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
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}