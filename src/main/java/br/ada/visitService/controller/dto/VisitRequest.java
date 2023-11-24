package br.ada.visitService.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VisitRequest {
    private String userId;
    private boolean newUser;
    private LocalDate visitDate;
    
}