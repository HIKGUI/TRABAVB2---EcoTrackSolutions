package br.unipar.programacaoweb.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float leitura;

    private String descricao;

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    @JsonIgnore
    private Sensor sensor;

}
