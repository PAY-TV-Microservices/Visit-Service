# Observações sobre o projeto de visita técnica
## Observações sobre o projeto
### Endpoint Requisitar Visita Técnica
    1. O payload de requisição descrito no readme corresponde ao que foi implementado
    2. O payload de response implementado possui o atributo Technician que não foi descrito na solução, porém este atributo não interfere no contrato. O ponto de observação aqui é referente a um dado que esta vindo diretamento do banco de dados.
    Devemos estar cientes ao que é disponibilizado como resposta à uma requisição, pois podemos disponibilizar dados sensíveis, ou seja, dados que não deveriam ser expostos ou somente utilizados internamente pela aplicação.
### Resources
    1. Não deixe seus acessos, como senhas de banco de dados expostos a não ser que seja algo proveniente de um docker ou uma autenticação fake.
### PaymentClient
    1. Vocês configuraram perfeitamente o WebClient para utilização da interface PaymentClient, porém não utilizaram a mesma no service de vocês.

    A solução no Service de vocês utilizando o PaymentClient, ficaria da seguinte forma:

```java
    @Autowired
    PaymentClient paymentClient;
    public VisitResponse saveNewVisit(VisitRequest visitRequest) throws UserNewException, PendingPaymentsException {
        PaymentResponse paymentResponse = paymentClient.checkOpenPayments(visitRequest.getUserId());

        boolean scheduleVisit = (!visitRequest.isNewUser()) && (paymentResponse != null && paymentResponse.getPendingPayments().isEmpty());
        ....
    }
```
    
