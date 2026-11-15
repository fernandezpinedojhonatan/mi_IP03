package tarea_IP03.tarea_IP03.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos de entrada para calcular el monto final de una venta")
public class BodegaRequestDTO {

    @Schema(description = "Nombre del producto", example = "Televisor LG 55\"")
    @NotBlank(message = "El nombre del producto es obligatorio y no puede estar vacío")
    private String producto;

    @Schema(description = "Cantidad de productos a comprar", example = "2")
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @Schema(description = "Precio unitario del producto (sin IGV)", example = "300.00")
    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

    @Schema(description = "Método de pago: TARJETA o EFECTIVO", example = "TARJETA")
    @NotBlank(message = "El método de pago es obligatorio")
    @Pattern(regexp = "TARJETA|EFECTIVO", message = "El método de pago solo puede ser TARJETA o EFECTIVO")
    private String metodoPago;
}