package tarea_IP03.tarea_IP03.controller;

import tarea_IP03.tarea_IP03.dto.VentaRequest;
import tarea_IP03.tarea_IP03.dto.VentaResponse;
import tarea_IP03.tarea_IP03.service.CalculadoraVenta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ventas", description = "Endpoint para el cálculo y gestión de ventas de la Bodega Don Pepe")
@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class BodegaController {

    private final CalculadoraVenta calculadoraVenta;

    @Operation(summary = "Calcular venta", description = "Calcula el subtotal, descuento, IGV, comisión y total a pagar de una venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos (campos vacíos, cantidad o precio menores a 0, método de pago incorrecto)")
    })
    @PostMapping("/calcular")
    public ResponseEntity<VentaResponse> calcularVenta(@Valid @RequestBody VentaRequest request) {

        // El servicio CalculadoraVenta hace las validaciones y los cálculos matemáticos
        VentaResponse response = calculadoraVenta.calcular(request);

        // Retornamos la respuesta con estado HTTP 200 (OK)
        return ResponseEntity.ok(response);
    }
}