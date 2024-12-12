package hospital.com.repositories;

import hospital.com.entities.MedicosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
@Async("customThreadPool")
public interface MedicosRepository extends JpaRepository<MedicosEntity, Long> {

    CompletableFuture<Optional<MedicosEntity>> findByNome(String nome);
    CompletableFuture<Optional<MedicosEntity>> findOneByMatricula(Long matricula);
    CompletableFuture<List<MedicosEntity>> findAllBy();
}
