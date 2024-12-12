package hospital.com.repositories;

import hospital.com.entities.PacientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface PacientesRepository extends JpaRepository<PacientesEntity, Long> {

    CompletableFuture<Optional<PacientesEntity>> findByNome(String nome);
    CompletableFuture<List<PacientesEntity>> findAllBy();
    CompletableFuture<Optional<PacientesEntity>> findOneById(Long id);
}
