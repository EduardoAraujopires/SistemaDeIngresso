# 🎫 Sistema de Venda de Ingressos - EventTicketing

[![Java](https://img.shields.io/badge/Java-17-blue)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)](https://www.postgresql.org/)
[![Swagger](https://img.shields.io/badge/Swagger-UI-brightgreen)](http://localhost:8080/swagger-ui.html)

API REST para gestão de eventos e venda de ingressos online, com foco em resolver problemas reais de e-commerce como **overbooking**, **concorrência** e **duplicidade de transações**.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.1
- Spring Data JPA
- Spring Validation
- PostgreSQL / H2 (testes)
- MapStruct
- Lombok
- Swagger / OpenAPI 3
- Maven

## ✨ Funcionalidades Principais

- CRUD completo de eventos
- Compra de ingressos com controle de estoque
- **Lock pessimista** para evitar overbooking
- **Idempotency Key** para prevenir cobranças duplicadas
- Tratamento global de exceções com respostas padronizadas
- DTOs e validações com `@Valid`
- Documentação automática com Swagger

## 📋 Endpoints Exemplos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/eventos` | Criar evento |
| GET | `/api/eventos` | Listar todos |
| GET | `/api/eventos/disponiveis` | Eventos com ingressos |
| POST | `/api/compras` | Comprar ingresso (header `Idempotency-Key`) |

## 🧪 Como Executar

```bash
# Clone o repositório
git clone https://github.com/EduardoAraujopires/event-ticketing.git

# Entre na pasta
cd event-ticketing

# Execute com Maven
mvn spring-boot:run
