package br.ada.visitService.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponse {
    private List<Object> pendingPayments; //Se estiver vazia pode marcar visita
}
