package br.ada.visitService.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisitResponse {
    private Long id;
    private String visitId;
    private LocalDate visitDate;
    private String userId;
}
