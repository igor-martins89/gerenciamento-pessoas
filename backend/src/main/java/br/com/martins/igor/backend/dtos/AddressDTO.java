package br.com.martins.igor.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotEmpty(message = "Mandatory field")
    @Size(min = 3, max= 120, message = "The length must be between {min} and {max} characters.")
    private String address;
    private String zipCode;
    @NotEmpty(message = "Mandatory field")
    @Size(min = 1, max= 10, message = "The length must be between {min} and {max} characters.")
    private String number;
    @NotEmpty(message = "Mandatory field")
    @Size(min = 2, max= 100, message = "The length must be between {min} and {max} characters.")
    private String city;

    private Integer type;

    public AddressDTO(Integer id, String address, String zipCode, String number, String city, Integer type) {
        this.id = id;
        this.address = address;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.type = type;
    }
}
