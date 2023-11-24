package br.ada.visitService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ada.visitService.model.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

}
