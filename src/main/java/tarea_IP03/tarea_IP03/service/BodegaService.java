package tarea_IP03.tarea_IP03.service;

import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;

import java.util.List;

public interface BodegaService {

    // POST: Calcular y guardar una nueva venta
    BodegaResponseDTO calcular(BodegaRequestDTO request);

    // GET: Listar todas las ventas
    List<BodegaResponseDTO> listarTodos();

    // GET: Buscar una venta por su ID
    BodegaResponseDTO buscarPorId(Long id);

    // PUT: Actualizar (recalcular) una venta existente
    BodegaResponseDTO actualizar(Long id, BodegaRequestDTO request);

    // DELETE: Eliminar una venta por su ID
    void eliminar(Long id);
}