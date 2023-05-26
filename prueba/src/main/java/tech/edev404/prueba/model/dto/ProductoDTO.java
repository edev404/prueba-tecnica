package tech.edev404.prueba.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"id", "codigo", "nombre", "cantidad", "valorCompra", "valorVenta", "fechaCreacion", "ultimaActualizacion"})
public class ProductoDTO {

    @JsonProperty("id")
    @JsonAlias("idProducto")
    private UUID id;

    @JsonProperty(access = Access.READ_ONLY, value = "nombre", required = false)
    private String codigo;

    @NotEmpty
    @JsonProperty(access = Access.READ_WRITE, value = "nombre", required = true)
    private String nombre;

    @NotEmpty
    @JsonProperty(access = Access.READ_WRITE, value = "cantidad", required = true)
    private BigDecimal cantidad;

    @NotEmpty
    @JsonAlias({"coste", "valorDeCompra"})
    @JsonProperty(access = Access.READ_WRITE, value = "valorCompra", required = true)
    private BigDecimal valorCompra;

    @NotEmpty
    @JsonAlias({"precio", "valorDeVenta"})
    @JsonProperty(access = Access.READ_WRITE, value = "valorCompra", required = true)
    private BigDecimal valorVenta;

    @NotEmpty
    @JsonProperty(access = Access.READ_WRITE, value = "disponible", required = true)
    private Boolean disponible;

    @JsonProperty(access = Access.READ_ONLY, value = "fechaCreacion", required = false)
    private LocalDateTime fechaCreacion;

    @JsonProperty(access = Access.READ_ONLY, value = "ultimaActualizacion", required = false)
    private LocalDateTime ultimaActualizacion;
    
}
