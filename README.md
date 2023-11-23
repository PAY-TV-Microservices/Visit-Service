# Visit-Service
Microsserviço responsável pelo controle das visitas técnicas realizadas para os assinantes dos pacotes de canais.


## Contratos
### VISITA-request-PAGAMENTO:
{
    "userId":"string",
},
{
    "userId":"string",
}

### PAGAMENTO-response-VISITA:
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

### ASSINATURA-request-VISITA:
[
    {
        //boolean
        "newUser":true,
        "userId":"string",
    }
]


