# AgilePop API

API para gerenciamento e divulgação de eventos da plataforma AgilePop.

---

## 📌 Descrição

   O AgilePop é uma API REST desenvolvida em Java com Spring Boot para permitir o cadastro, listagem e validação de eventos.  
   Esta API suporta validações de dados, documentação via Swagger/OpenAPI, e testes automatizados com JUnit.

---

## 🚀 Como rodar o projeto localmente

### Pré-requisitos

- Java 17 (ou superior)
- Maven 3.6+
- Git

### Passo a passo para desenvolvedores

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/agilepop.git
   cd agilepop
   ```

2. Compile e baixe dependências:

   ```bash
   mvn clean install
   ```

3. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. A API estará rodando em:

   ```http
   http://localhost:8080/api/eventos
   ```

5. Acesse a documentação Swagger para testar endpoints:

   ```http
   http://localhost:8080/swagger-ui.html
   ```

---

## 🧪 Rodando os testes automatizados

Para rodar todos os testes JUnit:

```bash
mvn test
```

Os testes validam cenários de criação de eventos, validações e listagem.

---

## 👩‍💻 Para o time de QA — Testes manuais

- Use o Swagger UI para testar os endpoints HTTP (GET, POST).
- Teste os seguintes casos:

  - Criar evento com dados válidos (deve retornar status 201 Created)
  - Criar evento sem título (deve retornar 400 Bad Request com mensagem de erro)
  - Criar evento com data no passado (deve retornar 400)
  - Criar evento com descrição muito longa (deve retornar 400)
  - Listar eventos (deve retornar status 200 OK e lista de eventos)

- Utilize ferramentas como [Postman](https://www.postman.com/downloads/) ou Insomnia para testar endpoints também.

---

## 📁 Estrutura do projeto

- `com.agilepop.model` — Entidade Evento
- `com.agilepop.controller` — Controller REST
- `com.agilepop.repository` — Interface JPA Repository
- `com.agilepop.exception` — Tratamento global de erros
- `com.agilepop` — Testes com JUnit e MockMvc

---

## 📞 Contato

Para dúvidas ou contribuições, entre em contato:  
Victor - Desenvolvedor  
Email: [contato@agilepop.com](mailto:contato@agilepop.com)  
GitHub: [https://github.com/AgilePopcorn/AgilePopApi](https://github.com/AgilePopcorn/AgilePopApi)

---

## 📜 Licença

Projeto aberto sob licença Apache 2.0.  
Veja detalhes em: [https://www.apache.org/licenses/LICENSE-2.0.html](https://www.apache.org/licenses/LICENSE-2.0.html)
