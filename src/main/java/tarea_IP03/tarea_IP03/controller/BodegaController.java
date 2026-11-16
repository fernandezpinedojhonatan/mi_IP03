package tarea_IP03.tarea_IP03.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;
import tarea_IP03.tarea_IP03.service.BodegaService;

import java.util.List;

@Tag(name = "Ventas", description = "Endpoint para el CRUD y cálculo de ventas de la Bodega Don Pepe")
@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class BodegaController {

    private final BodegaService bodegaService;

    @Operation(summary = "Calcular venta")
    @PostMapping("/calcular")
    public ResponseEntity<BodegaResponseDTO> calcularVenta(@Valid @RequestBody BodegaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaService.calcular(request));
    }

    @Operation(summary = "Listar ventas")
    @GetMapping
    public ResponseEntity<List<BodegaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(bodegaService.listarTodos());
    }

    @Operation(summary = "Buscar venta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.buscarPorId(id));
    }

    @Operation(summary = "Actualizar venta")
    @PutMapping("/{id}")
    public ResponseEntity<BodegaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody BodegaRequestDTO request) {
        return ResponseEntity.ok(bodegaService.actualizar(id, request));
    }

    @Operation(summary = "Eliminar venta")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        bodegaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}