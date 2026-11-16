package tarea_IP03.tarea_IP03.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;
import tarea_IP03.tarea_IP03.model.entity.Bodega;
import tarea_IP03.tarea_IP03.repository.BodegaRepository;
import tarea_IP03.tarea_IP03.service.BodegaService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;

    private static final double TASA_IGV = 0.18;
    private static final double TASA_COMISION_TARJETA = 0.03;
    private static final double LIMITE_DESCUENTO = 500.0;
    private static final double TASA_DESCUENTO = 0.10;

    @Override
    public BodegaResponseDTO calcular(BodegaRequestDTO request) {
        validarRequest(request);
        return mapToResponseDTO(bodegaRepository.save(crearEntidad(request)));
    }

    @Override
    public List<BodegaResponseDTO> listarTodos() {
        return bodegaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public BodegaResponseDTO actualizar(Long id, BodegaRequestDTO request) {
        validarRequest(request);
        Bodega bodegaExistente = bodegaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe venta con id: " + id));

        Bodega bodegaActualizada = crearEntidad(request);
        bodegaExistente.setProducto(bodegaActualizada.getProducto());
        bodegaExistente.setCantidad(bodegaActualizada.getCantidad());
        bodegaExistente.setPrecioUnitario(bodegaActualizada.getPrecioUnitario());
        bodegaExistente.setMetodoPago(bodegaActualizada.getMetodoPago());
        bodegaExistente.setSubtotal(bodegaActualizada.getSubtotal());
        bodegaExistente.setDescuento(bodegaActualizada.getDescuento());
        bodegaExistente.setIgv(bodegaActualizada.getIgv());
        bodegaExistente.setComision(bodegaActualizada.getComision());
        bodegaExistente.setTotalPagar(bodegaActualizada.getTotalPagar());

        return mapToResponseDTO(bodegaRepository.save(bodegaExistente));
    }

    @Override
    public void eliminar(Long id) {
        if (!bodegaRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe venta con id: " + id);
        }
        bodegaRepository.deleteById(id);
    }

    private void validarRequest(BodegaRequestDTO request) {
        if (request.getProducto() == null || request.getProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        if (request.getPrecioUnitario() == null || request.getPrecioUnitario() <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a 0.");
        }
        if (request.getMetodoPago() == null ||
                (!request.getMetodoPago().equals("TARJETA") && !request.getMetodoPago().equals("EFECTIVO"))) {
            throw new IllegalArgumentException("El método de pago solo puede ser TARJETA o EFECTIVO.");
        }
    }

    private Bodega crearEntidad(BodegaRequestDTO request) {
        double subtotal = request.getCantidad() * request.getPrecioUnitario();
        double descuento = subtotal > LIMITE_DESCUENTO ? subtotal * TASA_DESCUENTO : 0.0;
        double baseImponible = subtotal - descuento;
        double igv = baseImponible * TASA_IGV;
        double totalConIgv = baseImponible + igv;
        double comision = request.getMetodoPago().equals("TARJETA") ? totalConIgv * TASA_COMISION_TARJETA : 0.0;
        double totalPagar = totalConIgv + comision;

        return Bodega.builder()
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
    }

    private BodegaResponseDTO mapToResponseDTO(Bodega bodega) {
        return BodegaResponseDTO.builder()
                .id(bodega.getId())
                .producto(bodega.getProducto())
                .cantidad(bodega.getCantidad())
                .precioUnitario(bodega.getPrecioUnitario())
                .metodoPago(bodega.getMetodoPago())
                .subtotal(bodega.getSubtotal())
                .descuento(bodega.getDescuento())
                .igv(bodega.getIgv())
                .comision(bodega.getComision())
                .totalPagar(bodega.getTotalPagar())
                .createdAt(bodega.getCreatedAt()) // Corregido a camelCase
                .updatedAt(bodega.getUpdatedAt()) // Corregido a camelCase
                .build();
    }

    private Double redondear(Double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}