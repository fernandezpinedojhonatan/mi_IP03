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
import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaServiceImpl implements BodegaService {

    private final BodegaRepository bodegaRepository;

    // Constantes del negocio (Reglas del examen)
    private static final double TASA_IGV = 0.18;
    private static final double TASA_COMISION_TARJETA = 0.03;
    private static final double LIMITE_DESCUENTO = 500.0;
    private static final double TASA_DESCUENTO = 0.10;

    @Override
    public BodegaResponseDTO calcular(BodegaRequestDTO request) {

        // 1. Validaciones manuales (Punto 4 del examen)
        if (request.getProducto() == null || request.getProducto().trim().isEmpty()) {
            throw new ValidacionException("El nombre del producto no puede estar vacío.");
        }
        if (request.getCantidad() == null || request.getCantidad() <= 0) {
            throw new ValidacionException("La cantidad debe ser mayor a 0.");
        }
        if (request.getPrecioUnitario() == null || request.getPrecioUnitario() <= 0) {
            throw new ValidacionException("El precio unitario debe ser mayor a 0.");
        }
        if (request.getMetodoPago() == null ||
                (!request.getMetodoPago().equals("TARJETA") && !request.getMetodoPago().equals("EFECTIVO"))) {
            throw new ValidacionException("El método de pago solo puede ser TARJETA o EFECTIVO.");
        }

        // 2. Cálculos matemáticos
        double subtotal = request.getCantidad() * request.getPrecioUnitario();

        double descuento = 0.0;
        if (subtotal > LIMITE_DESCUENTO) {
            descuento = subtotal * TASA_DESCUENTO;
        }

        double baseImponible = subtotal - descuento;
        double igv = baseImponible * TASA_IGV;
        double totalConIgv = baseImponible + igv;

        double comision = 0.0;
        if (request.getMetodoPago().equals("TARJETA")) {
            comision = totalConIgv * TASA_COMISION_TARJETA;
        }

        double totalPagar = totalConIgv + comision;

        // 3. Guardar en Base de Datos
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

        // Guardamos en MySQL y retornamos la respuesta
        return mapToResponseDTO(bodegaRepository.save(bodega));
    }

    @Override
    public List<BodegaResponseDTO> listarTodos() {
        return bodegaRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Método auxiliar para convertir la Entidad al DTO (Igual que en tu MarcaServiceImpl)
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
                .created_at(bodega.getCreatedAt()) // Si tu Auditable usa getCreated_at(), cámbialo aquí
                .updated_at(bodega.getUpdatedAt()) // Si tu Auditable usa getUpdated_at(), cámbialo aquí
                .build();
    }

    // Método auxiliar para redondear a 2 decimales
    private Double redondear(Double valor) {
        return BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}