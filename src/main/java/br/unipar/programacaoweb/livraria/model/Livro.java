package br.unipar.programacaoweb.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    @NotNull
    private String titulo;

    @Min(value = 3, message = "O número de páginas deve ser maior ou igual a 3")
    private Integer numeroPaginas;

    @Size(min = 3, max = 50, message = "O gênero deve ter entre 3 e 50 caracteres")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonIgnore
    private Autor autor;
}

