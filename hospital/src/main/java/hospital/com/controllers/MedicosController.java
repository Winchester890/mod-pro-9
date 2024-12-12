package hospital.com.controllers;

import hospital.com.entities.MedicosEntity;
import hospital.com.services.MedicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/medicos")
@Async
public class MedicosController {
    @Autowired
    private final MedicosService medicosService;

    public MedicosController(MedicosService medicosService) {
        this.medicosService = medicosService;
    }

    @GetMapping
    public CompletableFuture<List<MedicosEntity>> findAll() {
       return this.medicosService.findAll();
    }

    @GetMapping("/{matricula}")
    public CompletableFuture<MedicosEntity> findByMatricula(@PathVariable("matricula") final Long matricula) {
        return this.medicosService.findByMatricula(matricula)
                .thenApply(x -> x.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Médico não encontrado"
                )));
    }

    @GetMapping("/nome/{nome}")
    public CompletableFuture<Optional<MedicosEntity>> findByNome(final String nome) {
        return this.medicosService.findByNome(nome);
    }

    @PostMapping("/adicionar")
    public void addMedico(@RequestBody final MedicosEntity medico) {
        this.medicosService.save(medico);
    }

    @PostMapping("/atualizar")
    public void update(@RequestBody final MedicosEntity medicosEntity) {
        this.medicosService.save(medicosEntity);
    }

    @DeleteMapping("/excluir")
    public void deleteByMatricula(@PathVariable("matricula") final Long matricula) {
        this.medicosService.deleteById(matricula);
    }
}
