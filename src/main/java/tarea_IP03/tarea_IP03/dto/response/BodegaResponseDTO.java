package tarea_IP03.tarea_IP03.dto.response;

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
public class BodegaResponseDTO {

    // ID de la venta (útil si guardas en la BD)
    private Long id;

    // Datos de la venta
    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private String metodoPago;

    // Resultados del cálculo
    private Double subtotal;
    private Double descuento;
    private Double igv;
    private Double comision;
    private Double totalPagar;

    // Campos de auditoría (heredados de tu clase Auditable)
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}