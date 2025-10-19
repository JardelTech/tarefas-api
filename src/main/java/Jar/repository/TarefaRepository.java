package Jar.repository; // O nome do seu pacote "jar.repository"

import org.springframework.data.jpa.repository.JpaRepository; // Importa o Model que você criou

import Jar.model.Tarefa;

// [REQ] Interface TarefaRepository utilizando JpaRepository [cite: 25, 40]
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    // Não precisa escrever mais nada!
    // O JpaRepository já cria os métodos:
    // - save()         (para Criar e Atualizar)
    // - findAll()      (para Consultar Todos)
    // - findById()     (para Consultar por ID)
    // - deleteById()   (para Remover)
}