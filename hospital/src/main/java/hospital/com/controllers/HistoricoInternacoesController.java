package hospital.com.controllers;

import hospital.com.adapter.HistoricoInternacoesAdapter;
import hospital.com.entities.HistoricoInternacoesEntity;
import hospital.com.entities.MedicosEntity;
import hospital.com.entities.PacientesEntity;
import hospital.com.models.HistoricoInternacoesPorMedico;
import hospital.com.models.HistoricoInternacoesPorPaciente;
import hospital.com.repositories.HistoricoInternacoesRepository;
import hospital.com.repositories.MedicosRepository;
import hospital.com.repositories.PacientesRepository;
import hospital.com.services.HistoricoInternacoesService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/historico_internacoes")
public class HistoricoInternacoesController {
    @Autowired
    private final HistoricoInternacoesRepository historicoInternacoesRepository;
    @Autowired
    private final MedicosRepository medicosRepository;
    @Autowired
    private final PacientesRepository pacientesRepository;
    @Autowired
    private final HistoricoInternacoesService historicoInternacoesService;
    private HistoricoInternacoesAdapter historicoInternacoesAdapter = new HistoricoInternacoesAdapter();

    public HistoricoInternacoesController(HistoricoInternacoesRepository historicoInternacoesRepository, MedicosRepository medicosRepository, PacientesRepository pacientesRepository, HistoricoInternacoesService historicoInternacoesService) {
        this.historicoInternacoesRepository = historicoInternacoesRepository;
        this.medicosRepository = medicosRepository;
        this.pacientesRepository = pacientesRepository;
        this.historicoInternacoesService = historicoInternacoesService;
    }

    @RolesAllowed("user")
    @GetMapping
    public List<HistoricoInternacoesEntity> findAll() {
        return this.historicoInternacoesRepository.findAll();
    }
    @RolesAllowed("user")
    @GetMapping("/{id}")
    public CompletableFuture<HistoricoInternacoesEntity> findById(@PathVariable("id") final Long id) {
        return this.historicoInternacoesService.findById(id)
                .thenApply(x -> x.orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Histórico não encontrado"
                )));
    }
    @RolesAllowed("user")
    @GetMapping("/descricao/{descricao}")
    public CompletableFuture<Optional<HistoricoInternacoesEntity>> findByDescricao(final String descricao) {
        return this.historicoInternacoesService.findByDescricao(descricao);
    }
    @RolesAllowed("user")
    @GetMapping("/por_medico")
    public List<HistoricoInternacoesPorMedico> findHistoricoInternacoesPorMedico() {
        List<MedicosEntity> medicos = medicosRepository.findAll();
        List<HistoricoInternacoesEntity> historicoInternacoes = historicoInternacoesRepository.findAll();
        List<HistoricoInternacoesPorMedico> historicoInternacoesPorMedicos = historicoInternacoesAdapter
                .historicoInternacoesPorMedicoAdapter(historicoInternacoes, medicos);
        return historicoInternacoesPorMedicos;
    }
    @RolesAllowed("user")
    @GetMapping("/por_paciente")
    public List<HistoricoInternacoesPorPaciente> findHistoricoInternacoesPorPaciente() {
        List<PacientesEntity> pacientes = pacientesRepository.findAll();
        List<HistoricoInternacoesEntity> historicoInternacoes = historicoInternacoesRepository.findAll();
        List<HistoricoInternacoesPorPaciente> historicoInternacoesPorPacientes = historicoInternacoesAdapter
                .historicoInternacoesPorPacienteAdapter(historicoInternacoes, pacientes);
        return historicoInternacoesPorPacientes;
    }
    @RolesAllowed("adm")
    @PostMapping("/adicionar")
    public void addHistorico(@RequestBody final HistoricoInternacoesEntity historico) {
        this.historicoInternacoesService.save(historico);
    }
    @RolesAllowed("adm")
    @PostMapping("/atualizar")
    public void update(@RequestBody final HistoricoInternacoesEntity historicoInternacoes) {
        this.historicoInternacoesService.save(historicoInternacoes);
    }
    @RolesAllowed("adm")
    @DeleteMapping("/excluir")
    public void deleteById(@PathVariable("id") final Long id) {
        this.historicoInternacoesService.deleteById(id);
    }
}