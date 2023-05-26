package tech.edev404.prueba.model.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"id", "nombre", "apellido", "email", "telefono", "enabled"})
public class UsuarioDTO {

    private UUID id;

    private String nombre;

    private String apellido;

    @Email
    private String email;
    
    private String telefono;

    @JsonIgnore
    private String password;

    @JsonIgnore
	private String authority;

    private Boolean enabled;
    
}
