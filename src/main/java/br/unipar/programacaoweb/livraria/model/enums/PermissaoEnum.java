package br.unipar.programacaoweb.livraria.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissaoEnum {

    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private Integer id;
    private String descricao;
}
