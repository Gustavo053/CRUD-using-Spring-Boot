package aula.aulaweb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogosRepository extends JpaRepository<Jogos, Long> {
    List<Jogos> findByAnoLancamento(String ano);
    List<Jogos> findByCategoria(String categoria);
}
