package hospital.com.services;

import hospital.com.entities.HistoricoInternacoesEntity;
import hospital.com.repositories.HistoricoInternacoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class HistoricoInternacoesService {

    private final HistoricoInternacoesRepository historicoInternacoesRepository;

    public HistoricoInternacoesService(HistoricoInternacoesRepository historicoInternacoesRepository) {
        this.historicoInternacoesRepository = historicoInternacoesRepository;
    }

    public CompletableFuture<List<HistoricoInternacoesEntity>> findAll() {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.historicoInternacoesRepository.findAllBy();
    }

    public CompletableFuture<Optional<HistoricoInternacoesEntity>> findById (Long id) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.historicoInternacoesRepository.findOneById(id);
    }

    public CompletableFuture<Optional<HistoricoInternacoesEntity>> findByDescricao (String descricao) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return this.historicoInternacoesRepository.findByDescricao(descricao);
    }

    public CompletableFuture<HistoricoInternacoesEntity> save (final HistoricoInternacoesEntity historicoInternacoes) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(historicoInternacoesRepository.save(historicoInternacoes));
    }

    public void deleteById (final Long id) {
        System.out.println("Service Thread: " + Thread.currentThread().getName());
        historicoInternacoesRepository.deleteById(id);
    }
}
