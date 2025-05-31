package br.unipar.programacaoweb.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String status;

    private String data_instalacao;

    private String unidade;


    @ManyToOne
    @JoinColumn(name = "estacao_id")
    @JsonIgnore
    private Estacao estacao;

    @OneToMany(mappedBy = "leitura", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.LAZY)
    private List<Leitura> leitura = new ArrayList<>();


}
