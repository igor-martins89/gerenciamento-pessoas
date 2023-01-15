package br.com.martins.igor.backend.dtos;

import br.com.martins.igor.backend.entities.Person;
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
public class PersonDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    @NotEmpty(message = "Mandatory field")
    @Size(min = 5, max= 120, message = "O tamanho deve ser entre {min} e {max} caracteres.")
    private String name;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @PastOrPresent(message = "Data de nascimento inválida")
    private Date birthDate;

    public PersonDTO(Person person){
        this.id = person.getId();
        this.name = person.getName();;
        this.birthDate = person.getBirthDate();
    }

    public static Page<PersonDTO> conversor(Page<Person> produtos){
        return produtos.map(PersonDTO::new);
    }

}
