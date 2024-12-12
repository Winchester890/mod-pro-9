package hospital.com.services;

import hospital.com.entities.PacientesEntity;
import hospital.com.repositories.PacientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class PacientesService {

    private final PacientesRepository pacientesRepository;

    public PacientesService(PacientesRepository pacientesRepository) {
        this.pacientesRepository = pacientesRepository;
    }

    public CompletableFuture<List<PacientesEntity>> findAll() {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.pacientesRepository.findAllBy();
    }

    public CompletableFuture<Optional<PacientesEntity>> findById (Long id) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.pacientesRepository.findOneById(id);
    }

    public CompletableFuture<Optional<PacientesEntity>> findByNome (String nome) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.pacientesRepository.findByNome(nome);
    }

    public CompletableFuture<PacientesEntity> save (final PacientesEntity pacientesEntity) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(pacientesRepository.save(pacientesEntity));
    }

    public void deleteById (final Long id) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        pacientesRepository.deleteById(id);
    }
}
