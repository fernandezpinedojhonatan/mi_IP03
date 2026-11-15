package tarea_IP03.tarea_IP03.service;

import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;

import java.util.List;

public interface BodegaService {

    // Método principal del examen
    BodegaResponseDTO calcular(BodegaRequestDTO request);

    // Método extra para listar el historial
    List<BodegaResponseDTO> listarTodos();
}