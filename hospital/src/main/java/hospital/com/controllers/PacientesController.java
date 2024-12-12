package hospital.com.controllers;

import hospital.com.entities.PacientesEntity;
import hospital.com.services.PacientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    @Autowired
    private final PacientesService pacientesService;

    public PacientesController(PacientesService pacientesService) {
        this.pacientesService = pacientesService;
    }

    @GetMapping
    public CompletableFuture<List<PacientesEntity>> findAll() {
        return this.pacientesService.findAll();
    }

    @GetMapping("/{id}")
    public CompletableFuture<PacientesEntity> findById(@PathVariable("id") final Long id) {
        return this.pacientesService.findById(id)
                .thenApply(x -> x.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Paciente não encontrado"
                )));
    }

    @GetMapping("/nome/{nome}")
    public CompletableFuture<Optional<PacientesEntity>> findByNome(final String nome) {
        return this.pacientesService.findByNome(nome);
    }

    @PostMapping("/adicionar")
    public void addPaciente(@RequestBody final PacientesEntity paciente) {
        this.pacientesService.save(paciente);
    }

    @PostMapping("/atualizar")
    public void update(@RequestBody final PacientesEntity pacientesEntity) {
        this.pacientesService.save(pacientesEntity);
    }

    @DeleteMapping("/excluir")
    public void deleteById(@PathVariable("id") final Long id) {
        this.pacientesService.deleteById(id);
    }
}
