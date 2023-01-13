package br.com.martins.igor.backend.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoEndereco {

    ENDERECO_PADRAO(1, "Endereço Padrão"),
    ENDERECO_ADICIONAL(2 , "Endereço Adicional");

    private  int cod;
    private String descricao;

    public static TipoEndereco toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for(TipoEndereco tipo : TipoEndereco.values()){
            if( cod.equals(tipo.getCod())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + cod);
    }


}
