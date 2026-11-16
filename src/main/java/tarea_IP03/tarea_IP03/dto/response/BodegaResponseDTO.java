package tarea_IP03.tarea_IP03.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos de salida con el detalle del cobro")
public class BodegaResponseDTO {

    @Schema(description = "ID de la venta", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Televisor LG")
    private String producto;

    @Schema(description = "Cantidad de productos", example = "2")
    private Integer cantidad;

    @Schema(description = "Precio unitario del producto", example = "300.00")
    private Double precioUnitario;

    @Schema(description = "Método de pago", example = "TARJETA")
    private String metodoPago;

    @Schema(description = "Subtotal de la venta", example = "600.00")
    private Double subtotal;

    @Schema(description = "Descuento aplicado", example = "60.00")
    private Double descuento;

    @Schema(description = "Monto del IGV", example = "97.20")
    private Double igv;

    @Schema(description = "Comisión por tarjeta", example = "19.12")
    private Double comision;

    @Schema(description = "Total final a pagar", example = "656.32")
    private Double totalPagar;

    // Campos en camelCase para Java, pero se muestran como snake_case en el JSON
    @JsonProperty("created_at")
    @Schema(description = "Fecha de creación", example = "2026-11-16T15:54:27")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @Schema(description = "Fecha de última modificación", example = "2026-11-16T15:54:27")
    private LocalDateTime updatedAt;
}