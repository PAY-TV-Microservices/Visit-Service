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

## :pushpin: Funcionalidades principais

- Marcar Visita Técnicas: Marca uma visita através de uma requisição feita pelo microsserviço Assinatura (quando um novo cliente assina um pacote) ou através de uma requisição POST da API. Recebe o id do usuário, um booleano indicando se o usuário é novo ou não e a data da assinatura.

- Visualizar Todas as Visitas Técnicas: Através de uma requisição GET retorna uma lista com todas as visitas técnicas cadastradas.

- Visualizar Visita Técnica por Id: Através de uma requisição GET retorna uma visita cadastrada após fornecimento do id da visita.
  
- Cancelar Visita Técnica: Cancela uma visita técnica através de uma requisição DELETE após informado do id da visita.
  
- Atribuir Visita Técnica: Atribui um técnico a visita técnica através de uma requisição POST após informado do id da visita e das informações do Tecnico.

## ⚙️ Como Executar
1. Clone o repositório em uma pasta de preferência
  ```
  https://github.com/PAY-TV-Microservices/Visit-Service
  ```
2. Dentro do projeto, rode 
  ```
  docker-compose up
  ```
3. Visualize a interface da API utilizando o Swagger UI ou outra plataforma de sua preferência <br>
> **Acesso ao Swagger UI** <br>
   Se preferir, você pode explorar e testar os endpoints da API acessando o Swagger UI. <br>
   Certifique-se de que o projeto esteja em execução e acesse a URL abaixo pelo seu navegador: <br>
   http://localhost:8080/swagger-ui/index.html#/

## ☑️ Contratos
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
## 📚 Documentação (endpoints)
### 🏠 Visit
  <summary> Cadastro (POST) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza o cadastro da visita no sistema | `http://localhost:8080/visit`  
  
  **Request Body**
  ```json
{
  "userId": "string",
  "newUser": false,
  "visitDate": "2023-11-24"
}
```
**Response**  
HTTP status: 201 CREATED
```json
{
  "id": 0,
  "visitId": "string",
  "visitDate": "2023-11-24",
  "userId": "string"
}
```
<summary> Consulta (GET) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Realiza a visualização de todas as visitas cadastradas no sistema | `http://localhost:8080/visit`   
  
  **Response**  
HTTP status: 200 OK
  ```json
[
  {
    "id": 0,
    "visitId": "string",
    "visitDate": "2023-11-24",
    "userId": "string"
  }
]
```

<summary> Atribuição (POST) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `POST` | Realiza a atribuição de uma visita técnica cadastrada no sistema a um técnico | `http://localhost:8080/visit/technician/visitId`   
  
  **Request Body**
  ```json
{
  "name": "string",
  "cpf": "string"
}
```
  **Response**  
HTTP status: 200 OK
  ```json
{
  "id": 0,
  "visitId": "string",
  "visitDate": "2023-11-24",
  "userId": "string"
}
```
HTTP status: 404 NOT FOUND
```json
{
  "statusResponse": 404,
  "message": "Visita não encontrada"
}
```
<summary> Consulta por Id (GET) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `GET` | Realiza a consulta de uma visita técnica cadastrada no sistema através do id da visita | `http://localhost:8080/visit/id/visitId`   
  
  **Response**  
HTTP status: 200 OK
  ```json
{
  "id": 0,
  "visitId": "string",
  "visitDate": "2023-11-24",
  "userId": "string",
  "active": true,
  "technician": {
    "id": 0,
    "name": "string",
    "technicianId": "string",
    "cpf": "string"
  }
}
```
HTTP status: 404 NOT FOUND
```json
{
  "statusResponse": 404,
  "message": "Visita não encontrada"
}
```
<summary> Cancelamento (DELETE) </summary>
    <br>

  | Método | Funcionalidade | URL |
  |---|---|---|
  | `DELETE` | Realiza o cancelamento de uma visita técnica cadastrada no sistema através do id da visita | `http://localhost:8080/visit/visitId`   
  
  **Response**  
HTTP status: 200 OK   

HTTP status: 404 NOT FOUND
```json
{
  "statusResponse": 404,
  "message": "Visita não encontrada"
}
``` 

> _O desenvolvimento dos Microsserviços de Fatura e Visita Técnica da PAY-TV foi realizado por [Dayane](https://github.com/acdayane), [Juliana](https://github.com/julianaando), [Karen](https://github.com/karenCLima), [Natalia](https://github.com/nataliagiacobo), [Raquel](https://github.com/raquelpcarvalho) e [Thaís](https://github.com/tdthais)._
