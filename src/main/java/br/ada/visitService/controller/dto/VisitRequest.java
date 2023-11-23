package br.ada.visitService.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class VisitRequest {
    private String userId;
    private boolean newUser;
    private LocalDate visitDate;
    
}