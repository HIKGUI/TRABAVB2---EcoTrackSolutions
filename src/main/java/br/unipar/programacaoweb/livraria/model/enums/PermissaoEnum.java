package br.unipar.programacaoweb.livraria.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissaoEnum {

    SUPER(1, "ADMIN"),
    COMUM(2, "USER");

    private Integer id;
    private String descricao;
}
