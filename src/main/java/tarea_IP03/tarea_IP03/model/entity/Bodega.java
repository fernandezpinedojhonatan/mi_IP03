package tarea_IP03.tarea_IP03.model.entity;

import com.todobarato.pcl.todobarato.model.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bodega_ventas") // Puedes cambiar el nombre si ya creaste la tabla como "ventas"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bodega extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "producto", nullable = false, length = 200)
    private String producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "metodo_pago", nullable = false, length = 20)
    private String metodoPago;

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;

    @Column(name = "descuento", nullable = false)
    private Double descuento;

    @Column(name = "igv", nullable = false)
    private Double igv;

    @Column(name = "comision", nullable = false)
    private Double comision;

    @Column(name = "total_pagar", nullable = false)
    private Double totalPagar;
}