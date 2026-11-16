package tarea_IP03.tarea_IP03.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bodega_ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bodega extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "metodo_pago", nullable = false, length = 20)
    private String metodoPago;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double descuento;

    @Column(nullable = false)
    private Double igv;

    @Column(nullable = false)
    private Double comision;

    @Column(name = "total_pagar", nullable = false)
    private Double totalPagar;
}