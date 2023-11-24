package br.ada.visitService.repository;

import br.ada.visitService.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

}
