package tarea_IP03.tarea_IP03.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Datos de salida con el detalle del cobro")
public class BodegaResponseDTO {
    private Long id;
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private String metodoPago;
    private Double subtotal;
    private Double descuento;
    private Double igv;
    private Double comision;
    private Double totalPagar;
}