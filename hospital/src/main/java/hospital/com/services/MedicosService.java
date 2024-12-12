package hospital.com.services;

import hospital.com.entities.MedicosEntity;
import hospital.com.repositories.MedicosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class MedicosService {

    private final MedicosRepository medicosRepository;

    public MedicosService(MedicosRepository medicosRepository) {
        this.medicosRepository = medicosRepository;
    }

    public CompletableFuture<List<MedicosEntity>> findAll() {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.medicosRepository.findAllBy();
    }

    public CompletableFuture<Optional<MedicosEntity>> findByMatricula (Long matricula) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.medicosRepository.findOneByMatricula(matricula);
    }

    public CompletableFuture<Optional<MedicosEntity>> findByNome (String nome) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.medicosRepository.findByNome(nome);
    }

    public CompletableFuture<MedicosEntity> save (final MedicosEntity medicosEntity) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(medicosRepository.save(medicosEntity));
    }

    public void deleteById (final Long matricula) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        medicosRepository.deleteById(matricula);
    }

}
