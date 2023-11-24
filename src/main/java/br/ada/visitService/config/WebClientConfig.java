package br.ada.visitService.config;

import br.ada.visitService.client.PaymentClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Value("${pagamento.url}")
    private String paymentUrl;


    @Bean
    WebClient webClient(){
        return WebClient.builder().baseUrl(paymentUrl)
                .build();
    }

    @Bean
    PaymentClient paymentClient (WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter
                        .forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(PaymentClient.class);
    }
}
