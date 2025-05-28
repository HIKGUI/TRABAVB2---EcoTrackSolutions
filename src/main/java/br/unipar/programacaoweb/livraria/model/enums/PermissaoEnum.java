package br.unipar.programacaoweb.livraria.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissaoEnum {

    SUPER(1, "SUPER"),
    COMUM(2, "COMUM");

    private Integer id;
    private String descricao;
}
