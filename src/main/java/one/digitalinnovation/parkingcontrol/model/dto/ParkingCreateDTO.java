package one.digitalinnovation.parkingcontrol.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingCreateDTO {

	@NotEmpty(message = "Preencha o campo corretamente.")
	@Size(min = 8, max = 8, message = "'${validatedValue}' precisa ter {max} caracteres.")
	private String license;
	
	@NotEmpty(message = "Preencha o campo corretamente.")
	@Size(min = 2, max = 2, message = "'${validatedValue}' precisa ter {max} caracteres.")
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Somente letras")
    private String state;
	
	@NotEmpty(message = "Preencha o campo corretamente.")
	@Size(min = 3, max = 30, message = "'${validatedValue}' precisa ter entre {min} e {max} caracteres.")
    private String model;
	
	@NotEmpty(message = "Preencha o campo corretamente.")
	@Size(min = 3, max = 30, message = "'${validatedValue}' precisa ter entre {min} e {max} caracteres.")
    private String color;
    
}
