package br.unipar.programacaoweb.livraria.model;

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

    @OneToMany(mappedBy = "leitura", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.LAZY)
    private List<Leitura> leitura = new ArrayList<>();


}
