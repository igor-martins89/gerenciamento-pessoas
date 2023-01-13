package br.com.martins.igor.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 3, max= 120, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String logradouro;
    private String cep;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 1, max= 10, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String numero;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 2, max= 100, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String cidade;

    private Integer tipo;

    public EnderecoDTO(Integer id, String logradouro, String cep, String numero, String cidade, Integer tipo) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.tipo = tipo;
    }
}
