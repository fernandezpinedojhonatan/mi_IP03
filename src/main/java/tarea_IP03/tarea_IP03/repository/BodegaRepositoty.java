package tarea_IP03.tarea_IP03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tarea_IP03.tarea_IP03.model.entity.Bodega;

import java.util.List;

@Repository
public interface BodegaRepositoty extends JpaRepository<Bodega, Long> {
    // Buscar ventas por el nombre del producto
    List<Bodega> findByProducto(String producto);

    // Buscar ventas por método de pago (TARJETA o EFECTIVO)
    List<Bodega> findByMetodoPago(String metodoPago);
}
