package br.com.martins.igor.backend.dtos;

import br.com.martins.igor.backend.entities.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min = 5, max= 120, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String nome;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @PastOrPresent(message = "Data de nascimento inválida")
    private Date dataNascimento;

    public PessoaDTO (Pessoa pessoa){
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();;
        this.dataNascimento = pessoa.getDataNascimento();
    }

    public static Page<PessoaDTO> conversor(Page<Pessoa> produtos){
        return produtos.map(PessoaDTO::new);
    }

}
