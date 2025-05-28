package br.unipar.programacaoweb.livraria.repository;

import br.unipar.programacaoweb.livraria.model.Livro;
import br.unipar.programacaoweb.livraria.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByTipoContainingIgnoreCase(String tipo);

//    List<Livro> findByTituloContainingIgnoreCase(String titulo);
//
//    List<Livro> findByGeneroContainingIgnoreCase(String genero);
//
//    @Query("SELECT t FROM Livro t WHERE t.titulo = :genero AND t.numeroPaginas >= :numeroPaginas")
//    List<Livro> findByGeneroeNumeroPaginas(@Param("genero") String genero,
//                                           @Param("numeroPaginas") Integer numeroPaginas);
}
