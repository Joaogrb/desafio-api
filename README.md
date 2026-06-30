# Sobre

A aplicação foi desenvolvida utilizando Java Spring Boot e expõe uma API REST para realização de transferências entre contas.  
As contas são inseridas ao iniciar a aplicação.  
É possível buscá-las através do endpoint GET /accounts  
Após é só realizar a criação da transferência através do endpoint POST /accounts/{accountId}/transactions  

OBS: Só é permitido realizar transferências para contas de titularidades diferentes.

# Configuração e Instalação

1. Clone o repositório:
```bash
git clone https://github.com/Joaogrb/desafio-api/
```

2. Entre na pasta do projeto:
```bash
cd desafio-api
```

# Como Executar(Via Terminal)

No Linux / macOS  
``` bash
./mvnw spring-boot:run
```

No Windows (CMD / PowerShell)  
```bash
mvnw.cmd spring-boot:run
```

# Acessando a Aplicação

Após a inicialização, a API estará rodando localmente.  
Para executar os requests acessar a documentação da API(OpenAPI/Swagger):  
http://localhost:8080/swagger-ui.html



