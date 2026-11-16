package tarea_IP03.tarea_IP03.service;

import tarea_IP03.tarea_IP03.dto.request.BodegaRequestDTO;
import tarea_IP03.tarea_IP03.dto.response.BodegaResponseDTO;

public interface BodegaService {
    BodegaResponseDTO calcular(BodegaRequestDTO request);
}