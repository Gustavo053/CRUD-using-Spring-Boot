package aula.aulaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogosService {

    private JogosRepository jogosRepository;

    @Autowired
    public void setJogosRepository(JogosRepository jogosRepository) {
        this.jogosRepository = jogosRepository;
    }

    public List<Jogos> findAll() {
        return jogosRepository.findAll();
    }

    public Jogos findOne(Long id) {
        return jogosRepository.getOne(id);
    }

    public void add(Jogos jogos) {
        jogosRepository.save(jogos);
    }

    public void delete(Long id) {
        jogosRepository.deleteById(id);
    }
}
