package br.ada.visitService.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VisitRequest {
    private String userId;
    private boolean newUser;
    
}