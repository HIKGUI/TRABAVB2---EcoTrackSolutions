package br.unipar.programacaoweb.livraria.repository;

import br.unipar.programacaoweb.livraria.model.Estacao;
import br.unipar.programacaoweb.livraria.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacaoRepository extends JpaRepository<Estacao, Long> {

    List<Estacao> findByNomeContainingIgnoreCase(String nome);
}
