package br.unipar.programacaoweb.livraria.repository;

import br.unipar.programacaoweb.livraria.model.Leitura;
import br.unipar.programacaoweb.livraria.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {

    @Query("SELECT AVG(l.leitura) FROM Leitura l WHERE l.sensor.estacao.id = :estacaoId")
    Double mediaLeiturasPorEstacao(@Param("estacaoId") Long estacaoId);
}
