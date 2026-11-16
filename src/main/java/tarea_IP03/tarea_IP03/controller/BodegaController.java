package tarea_IP03.tarea_IP03.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;
import tarea_IP03.tarea_IP03.service.BodegaService;

@Tag(name = "Ventas", description = "Endpoint para el cálculo de ventas de la Bodega Don Pepe")
@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaService bodegaService;

    @Operation(summary = "Calcular venta", description = "Calcula subtotal, descuento, IGV, comisión y total a pagar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping("/calcular")
    public ResponseEntity<BodegaResponseDTO> calcularVenta(@Valid @RequestBody BodegaRequestDTO request) {
        BodegaResponseDTO response = bodegaService.calcular(request);
        return ResponseEntity.ok(response);
    }
}