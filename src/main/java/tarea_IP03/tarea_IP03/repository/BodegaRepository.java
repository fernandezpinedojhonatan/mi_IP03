package tarea_IP03.tarea_IP03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tarea_IP03.tarea_IP03.model.entity.Bodega;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}