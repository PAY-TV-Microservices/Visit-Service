package br.ada.visitService.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "technicians")
public class Technician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "technician_id", nullable = false)
    private String technicianId;

    @Column(name = "cpf", nullable = false)
    private String cpf;
}
