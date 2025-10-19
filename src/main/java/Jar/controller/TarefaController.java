package Jar.controller; // O nome do seu pacote "jar.controller"

import java.util.List; // Importa seu Model
import java.util.Optional; // Importa seu Repository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Jar.model.Tarefa;
import Jar.repository.TarefaRepository;

@RestController // [REQ] Define que esta classe é um controlador REST [cite: 27]
@RequestMapping("/tarefas") // Define que a URL base para todos os métodos será "http://localhost:8080/tarefas"
public class TarefaController {

    @Autowired // [REQ] Injeção de dependência do Spring Data JPA [cite: 25]
    private TarefaRepository tarefaRepository;

    /**
     * Requisito: Criar uma tarefa [cite: 12]
     * Método: POST
     * URL: http://localhost:8080/tarefas
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Retorna o status 201 (Created)
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        // @RequestBody: Pega o JSON enviado no corpo da requisição e transforma em um objeto Tarefa
        return tarefaRepository.save(tarefa);
    }

    /**
     * Requisito: Consultar todas as tarefas cadastradas [cite: 16]
     * Método: GET
     * URL: http://localhost:8080/tarefas
     */
    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    /**
     * Requisito: Consultar uma tarefa específica pelo ID [cite: 17]
     * Método: GET
     * URL: http://localhost:8080/tarefas/{id} (ex: /tarefas/1)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        // @PathVariable: Pega o "id" que veio na URL
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        
        if (tarefa.isPresent()) {
            return ResponseEntity.ok(tarefa.get()); // Retorna 200 (OK) com a tarefa
        }
        return ResponseEntity.notFound().build(); // Retorna 404 (Not Found)
    }

    /**
     * Requisito: Atualizar uma tarefa existente [cite: 18]
     * Método: PUT
     * URL: http://localhost:8080/tarefas/{id} (ex: /tarefas/1)
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        // Busca a tarefa pelo ID
        return tarefaRepository.findById(id)
                .map(tarefa -> { // Se encontrar...
                    // Atualiza os campos
                    tarefa.setNome(tarefaAtualizada.getNome());
                    tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
                    tarefa.setResponsavel(tarefaAtualizada.getResponsavel());
                    
                    // Salva as alterações no banco
                    Tarefa tarefaSalva = tarefaRepository.save(tarefa);
                    return ResponseEntity.ok(tarefaSalva); // Retorna 200 (OK) com a tarefa atualizada
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404
    }

    /**
     * Requisito: Remover uma tarefa [cite: 20]
     * Método: DELETE
     * URL: http://localhost:8080/tarefas/{id} (ex: /tarefas/1)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 (No Content), sucesso sem corpo de resposta
    public void removerTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
}