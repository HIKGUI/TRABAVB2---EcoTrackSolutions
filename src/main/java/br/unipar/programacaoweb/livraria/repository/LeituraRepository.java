package br.unipar.programacaoweb.livraria.repository;

import br.unipar.programacaoweb.livraria.model.Leitura;
import br.unipar.programacaoweb.livraria.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeituraRepository extends JpaRepository<Leitura, Long> {

//    List<Leitura> findByTipoContainingIgnoreCase(String );

//    List<Livro> findByTituloContainingIgnoreCase(String titulo);
//
//    List<Livro> findByGeneroContainingIgnoreCase(String genero);
//
//    @Query("SELECT t FROM Livro t WHERE t.titulo = :genero AND t.numeroPaginas >= :numeroPaginas")
//    List<Livro> findByGeneroeNumeroPaginas(@Param("genero") String genero,
//                                           @Param("numeroPaginas") Integer numeroPaginas);
}
