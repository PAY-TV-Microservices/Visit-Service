package br.ada.visitService.client;

import br.ada.visitService.controller.dto.PaymentResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;


public interface PaymentClient {

    @GetExchange(value = "/pendingPayments/{userId}")
    PaymentResponse checkOpenPayments(@PathVariable(value = "userId") String userId);

}
