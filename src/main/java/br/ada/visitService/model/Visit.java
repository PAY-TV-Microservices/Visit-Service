package br.ada.visitService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_id", nullable = false)
    private Long visitId;//verificar se será uma string

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "user_id", nullable = false)
    private String userId;

    private Boolean active;

    //TODO Colocar técnico
    //@JoinColumn(name = " technician_id", nullable = false)
    //private Technician technician;
    //
}
