package hospital.com.repositories;

import hospital.com.entities.HistoricoInternacoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
public interface HistoricoInternacoesRepository extends JpaRepository<HistoricoInternacoesEntity, Long> {

    CompletableFuture<Optional<HistoricoInternacoesEntity>> findByDescricao(String descricao);
    CompletableFuture<List<HistoricoInternacoesEntity>> findAllBy();
    CompletableFuture<Optional<HistoricoInternacoesEntity>> findOneById(Long id);
}
