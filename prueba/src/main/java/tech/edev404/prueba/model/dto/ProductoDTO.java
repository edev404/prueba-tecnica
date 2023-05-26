package tech.edev404.prueba.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "codigo", "nombre", "cantidad", "valorCompra", "valorVenta", "fechaCreacion", "ultimaActualizacion"})
public class ProductoDTO {

    @JsonProperty("id")
    @JsonAlias("idProducto")
    private UUID id;

    @JsonProperty( value = "codigo", required = false)
    private String codigo;

    @NotEmpty
    @JsonProperty( value = "nombre", required = true)
    private String nombre;

    @NotEmpty
    @JsonProperty( value = "cantidad", required = true)
    private BigDecimal cantidad;

    @NotEmpty
    @JsonAlias({"coste", "valorDeCompra"})
    @JsonProperty(value = "valorCompra", required = true)
    private BigDecimal valorCompra;

    @NotEmpty
    @JsonAlias({"precio", "valorDeVenta"})
    @JsonProperty(value = "valorVenta", required = true)
    private BigDecimal valorVenta;

    @NotEmpty
    @JsonProperty( value = "disponible", required = true)
    private Boolean disponible;

    @JsonProperty( value = "fechaCreacion", required = false)
    private LocalDateTime fechaCreacion;

    @JsonProperty(value = "ultimaActualizacion", required = false)
    private LocalDateTime ultimaActualizacion;
    
}
