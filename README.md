# Visit Service

## :paperclips: Sobre
Visit Service é um projeto desenvolvido como parte do curso Back-end Java da [Ada](https://ada.tech/) no módulo "Arquitetura de Software Ágil II".<br>
A proposta é implementar um  microsserviço responsável pelo controle das visitas técnicas realizadas para os assinantes dos pacotes de canais.

## :man_technologist: Conhecimentos aplicados
- **Linguagem de Programação:** Java 17
- **Framework:** Spring Boot
- **Banco de Dados:** PostgreSQL
- **Tecnologias utilizadas:** OpenAPI(Swagger), RabbitMQ
- **Arquitetura:** O projeto segue os princípios de SOLID, DDD e adota o padrão de arquitetura MVC (Model-View-Controller).
- **Metodologia de Desenvolvimento:** Scrum
- **Gerenciamento do Projeto:** Trello

## Contratos
### VISITA-request-PAGAMENTO:
```json
{
    {
         "userId":"string",
    },
    {
        "userId":"string",
    }
}
```
### PAGAMENTO-response-VISITA:
```json
{ 
    //lista
    [
        {
            "invoiceId":"string",
        },
        {
            "invoiceId":"string",
        },
    ]
}
```
### ASSINATURA-request-VISITA:
```json
[
    {
        //boolean
        "newUser":true,
        "userId":"string",
    }
]
```

