package br.ada.visitService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import org.hibernate.annotations.Where;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "visits", uniqueConstraints = @UniqueConstraint(columnNames = "visit_id"))
@Where(clause = "active is true")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "visit_id", nullable = false)
    private String visitId;

    @Column(name = "visit_date", nullable = false, columnDefinition = "DATE")
    private LocalDate visitDate;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;
}
