package br.com.martins.igor.backend.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TypeAddress {

    DEFAULT_ADDRESS(1, "Default Address"),
    ADITIONAL_ADDRESS(2 , "Aditional Address");

    private  int cod;
    private String desc;

    public static TypeAddress toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for(TypeAddress tipo : TypeAddress.values()){
            if( cod.equals(tipo.getCod())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid ID: " + cod);
    }


}
