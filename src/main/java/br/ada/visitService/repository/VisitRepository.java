package br.ada.visitService.repository;

import br.ada.visitService.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query(value = "SELECT * FROM VISITS WHERE ID = :visitId", nativeQuery = true)
    Visit findVisitById(Long visitId);
}
