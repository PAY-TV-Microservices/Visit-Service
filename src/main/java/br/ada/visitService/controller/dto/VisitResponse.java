package br.ada.visitService.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import br.ada.visitService.model.Technician;

@Getter
@Setter
public class VisitResponse {
    private Long id;
    private String visitId;
    private LocalDate visitDate;
    private String userId;
    private Technician technician;
}
