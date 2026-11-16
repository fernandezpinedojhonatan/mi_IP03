package tarea_IP03.tarea_IP03.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;
import tarea_IP03.tarea_IP03.exception.ValidacionException;
import tarea_IP03.tarea_IP03.model.entity.Bodega;
import tarea_IP03.tarea_IP03.repository.BodegaRepository;
import tarea_IP03.tarea_IP03.service.BodegaService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;

    @Override
    public BodegaResponseDTO calcular(BodegaRequestDTO request) {
        // Validaciones personalizadas exigidas en el examen
        if (request.getProducto() == null || request.getProducto().trim().isEmpty()) {
            throw new ValidacionException("El nombre del producto no puede estar vacío.");
        }
        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new ValidacionException("La cantidad debe ser mayor a 0.");
        }
        if (request.getPrecioUnitario() == null || request.getPrecioUnitario() <= 0) {
            throw new ValidacionException("El precio unitario debe ser mayor a 0.");
        }
        if (request.getMetodoPago() == null || (!request.getMetodoPago().equals("TARJETA") && !request.getMetodoPago().equals("EFECTIVO"))) {
            throw new ValidacionException("El método de pago solo puede ser TARJETA o EFECTIVO.");
        }

        // Cálculos
        double subtotal = request.getCantidad() * request.getPrecioUnitario();
        double descuento = subtotal > 500 ? subtotal * 0.10 : 0.0;
        double baseImponible = subtotal - descuento;
        double igv = baseImponible * 0.18;
        double totalConIgv = baseImponible + igv;
        double comision = request.getMetodoPago().equals("TARJETA") ? totalConIgv * 0.03 : 0.0;
        double totalPagar = totalConIgv + comision;

        // Guardar en BD
        Bodega bodega = Bodega.builder()
                .producto(request.getProducto().trim())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .metodoPago(request.getMetodoPago())
                .subtotal(redondear(subtotal))
                .descuento(redondear(descuento))
                .igv(redondear(igv))
                .comision(redondear(comision))
                .totalPagar(redondear(totalPagar))
                .build();

        Bodega guardada = bodegaRepository.save(bodega);

        // Retornar respuesta
        return BodegaResponseDTO.builder()
                .id(guardada.getId())
                .producto(guardada.getProducto())
                .cantidad(guardada.getCantidad())
                .precioUnitario(guardada.getPrecioUnitario())
                .metodoPago(guardada.getMetodoPago())
                .subtotal(guardada.getSubtotal())
                .descuento(guardada.getDescuento())
                .igv(guardada.getIgv())
                .comision(guardada.getComision())
                .totalPagar(guardada.getTotalPagar())
                .build();
    }

    private Double redondear(Double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}